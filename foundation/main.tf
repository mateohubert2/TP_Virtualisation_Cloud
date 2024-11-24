variable "project_id" {
  type        = string
  description = "PolyCalculator"
}

terraform {
  required_providers {
    scaleway = {
      source = "scaleway/scaleway"
    }
  }
  required_version = ">= 0.13"
}

provider "scaleway" {
  zone   = "fr-par-1"
  region = "fr-par"
}

resource "scaleway_registry_namespace" "registre_de_conteneur" {
  name        = "main-registry-container"
  description = "Main container registry"
  is_public   = false
}

resource "scaleway_vpc_private_network" "pn" {}

resource "scaleway_k8s_cluster" "cluster_kubernetes" {
  name                        = "k8s-cluster"
  version                     = "1.29.1"
  cni                         = "cilium"
  private_network_id          = scaleway_vpc_private_network.pn.id
  delete_additional_resources = false
}

resource "scaleway_k8s_pool" "pool" {
  cluster_id = scaleway_k8s_cluster.cluster_kubernetes.id
  name       = "tf-pool"
  node_type  = "DEV1-M"
  size       = 3
}

variable "database_names" {
  type    = list(string)
  default = ["bdd_production", "bdd_development"]
}

resource "scaleway_rdb_instance" "bdd_instances" {
  count          = length(var.database_names)
  name           = var.database_names[count.index]
  node_type      = "DB-DEV-S"
  engine         = "PostgreSQL-15"
  is_ha_cluster  = true
  disable_backup = true
  user_name      = "mateo"
  password       = "mateo"
}

resource "scaleway_rdb_database" "databases" {
  count       = length(var.database_names)
  instance_id = scaleway_rdb_instance.bdd_instances[count.index].id
  name        = var.database_names[count.index]
}

variable "dns_names" {
  type    = list(string)
  default = ["calculatrice-hubert-polytech-dijon.kiowy.net", "calculatrice-dev-hubert-polytech-dijon.kiowy.net"]
}

resource "scaleway_domain_record" "dns" {
  count    = length(var.dns_names)
  dns_zone = "domain.tld"
  name     = var.dns_names[count.index]
  type     = "A"
  data     = "1.2.3.4"
  ttl      = 3600
}

variable "load_names" {
  type    = list(string)
  default = ["load_balancer_production", "load_balancer_development"]
}

resource "scaleway_lb_ip" "load_balancer_instances" {
  count = length(var.load_names)
  zone  = "fr-par-1"
}

resource "scaleway_lb" "base" {
  count  = length(var.load_names)
  ip_ids = [scaleway_lb_ip.load_balancer_instances[count.index].id]
  zone   = scaleway_lb_ip.load_balancer_instances[count.index].zone
  type   = "LB-S"
  name   = var.load_names[count.index]
}
