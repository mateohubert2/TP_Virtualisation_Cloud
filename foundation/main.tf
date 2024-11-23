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