# TP_Virtualisation_Cloud
HUBERT Matéo

# Difficultés
Durant ce TP, j'ai rencontré quelques difficultés. Dans un premier temps, pour faire fonctionner RabbitMQ depuis Docker tout en ayant l'interface de management, j'ai dû installer le script rabbitmq_erlang.sh pour faire fonctionner le tout.

Ensuite, quand je suis passé de la version full local à l'intégration sur Docker, j'ai eu du mal à réussir à contacter les services RabbitMQ et Redis depuis le backend et le consumer. J'ai donc dû mettre en place un réseau privé dans Docker pour que la connexion soit possible. Un autre souci est que je n'avais pas mis la même version de la JDK dans le Dockerfile que sur ma machine locale et malheureusement, dans les versions supérieures, la fonction eval est dépréciée, ce qui empêchait le fonctionnement du consumer sans générer de message d'erreur. Cela m'a pris plusieurs jours pour localiser le problème.

Pour finir, j'ai également eu du mal à connecter le backend et le consumer à RabbitMQ et Redis sur Kubernetes, mais comme j'avais déjà eu le même souci sur Docker, j'ai pu le régler plus rapidement.

Malgré cela, la calculatrice est bien accessible et fonctionne bien sur Kubernetes. Cependant, pour des raisons obscures, il est possible que le résultat ne s'affiche pas environ 1 fois sur 10.
