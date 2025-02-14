# Backend:
## App.java:
![image](https://github.com/user-attachments/assets/40243eda-3a9d-41b5-8e1b-fc02d28159df)


Le fichier **App.java** contient la déclaration du webserver utilisé pour faire fonctionner les API. Le port d'écoute du webserver est le **port 8080**. L'ensemble des fichiers du dossier webserver sont les fichiers du projet [PolyNames](https://github.com/mateohubert2/PolyNames). Pour que le backend fonctionne correctement sur kubernetes, il faut indiquer l'adresse IP du service de rabbitmq et de redis sinon, la connexion ne se fait pas et l'application ne fonctionne pas voir les pods ne se lance pas.
### API:
**`polycalculator/id/:id`** Cette API utilise la méthode **GET**. Il faut fournir l'id d'un calcul qui a été réalisé afin d'en récupérer le résultat. Cette API appelle la fonction [GetCalculResult](##GetCalculResult) de [CalculController.java](#CalculController.java.).

**`polycalculator/calcul/:calcul`** Cette API utilise la méthode **POST**. Il faut fournir le calcul à réaliser en respectant les correspondances ci-dessous:
| Calculatrice | API |
|-----------|-----------|
| 0  | 0  |
| 1  | 1  |
| 2  | 2  |
| 3  | 3  |
| 4  | 4  |
| 5  | 5  |
| 6  | 6  |
| 7  | 7  |
| 8  | 8  |
| 9  | 9  |
| .  | .  |
| +  | +  |
| -  | -  |
| *  | *  |
| /  | div  |
| (  | parl  |
| )  | pard  |


Cette API appelle la fonction [CalculProcess](##CalculProcess) de [CalculController.java](#CalculController.java).

# CalculController:
## CalculProcess:
![image](https://github.com/user-attachments/assets/8c8ae4f3-008c-4452-9d9e-9056cf6c899b)


La fonction CalculProcess créée une **request** qui correspond au context qui fait la requête sur l'API ainsi qu'une **response** pour pouvoir répondre après le traitement de la requête. Dans un premier temps, on récupère le calcul en tant que **String** dans **calculString**. Ensuite, on remplace tous les mots par leur symbole. Dans le calcul transmis à l'API, 3 opérateurs sont des mots, car sinon il y a une erreur de route lors de l'envoi. Ensuite, on envoie l'id et le calcul à faire à RabbitMQ. L'id du calcul est renvoyé comme réponse.


## GetCalculResult:
![image](https://github.com/user-attachments/assets/54448cad-ca4f-4fae-927b-598cc56e6973)


La fonction GetCalculResult créée une **request** qui correspond au context qui fait la requête sur l'API ainsi qu'une **response** pour pouvoir répondre après le traitement de la requête. Dans un premier temps, on récupère l'id de la requête. On demande ensuite à Redis le résultat en fonction de l'id et on renvoie le résultat comme réponse.

# Consumer
![image](https://github.com/user-attachments/assets/31a6b43a-3f92-401f-a1c9-25ea8b8afa4c)

Dans un premier temps, le consumer se connecte à RabbitMQ plus précisement sur le même channel que le backend. 

![image](https://github.com/user-attachments/assets/f4f41c2b-238f-4b2e-bd32-84e64d23317e)

Ensuite, une fois qu'un calcul arrive dans la queue depuis le backend, le consumer le récupère, le nettoie (on garde uniquement l'id et le calcul à faire). Grâce à la fonction eval, on fait le calcul puis on envoie à Redis, l'id et le calcul.
# Redis
La nomenclature pour stocker le résultat est simplement la suivante: (id, résultat)

# Frontend:

![image](https://github.com/user-attachments/assets/21158d7b-e4c6-446e-bfad-d88cd760f39e)


## index.html:
Le fichier index.html est extrêmement simple. Il y a les différents boutons de créés, chacun avec un id pour pouvoir déclarer des EventListener dessus. 

## style.css:
Le fichier style.css permet d'avoir une coloration des différents boutons et de la calculatrice en général.

## script.js:
![image](https://github.com/user-attachments/assets/4392d652-1017-4c71-a9de-7166776bc790)

Le fichier script.js permet quant à lui de faire fonctionner la calculatrice avec le backend. Pour chaque bouton, un  EventListener sur le click est déclaré.

Ensuite, la variable calcul représente le calcul à envoyer à l'API. Elle est donc de la forme: "1+parl2div4pard*3". La variable calculDisplay est quant à elle destinée à être affichée sur l'écran de la calculatrice. Elle est donc de la forme: 1+(2/4)*3.

![image](https://github.com/user-attachments/assets/5cb8a84c-fdc9-4835-9157-2e62c6aa88c5)

Un bouton particulier est le bouton DEL. En effet, quand il faut supprimer le dernier élément ajouté, il y a une nuance entre calcul et calculDisplay. Pour calculDisplay, il suffit de supprimer le dernier caractère avec **calculDisplay  =  calculDisplay.slice(0, -1)**. Cependant pour calcul qui est envoyé à l'API, un **/** étant **div** et une **(** etant **parl ou pard**, il faut supprimer entre 3 et 4 caractères pour avoir un calcul correct. La variable **lastKey** permet donc de savoir la dernière touche à avoir été appuyée pour savoir s'il faut supprimer 1, 3 ou 4 caractères.

![image](https://github.com/user-attachments/assets/ebe7bcdf-cec4-412a-8103-cbcc60acc469)

A chaque fois qu'une touche est appuyée, la fonction **displayResult** est appelée. Elle permet simplement de mettre à jour l'affichage de la calculatrice.

![image](https://github.com/user-attachments/assets/00687ac7-fdab-4b81-8c2a-9b2a5dd9917e)

Le dernier bouton important est le bouton **=**. Quand on clique dessus, on fait appel dans un premier temps à [sendCalcul](##sendCalcul) de [CalculService.java](##CalculService.java) puis [getResult](##getResult) de [CalculService.java](##CalculService.java) .

## CalculService.java:

![image](https://github.com/user-attachments/assets/66737e8d-0042-48bd-a6f1-918315d83d6e)


La fonction **sendCalcul**, envoie une requête en mode **POST** à l'API **`/polycalculator/calcul/:calcul`** du backend en passant comme paramètre le calcul sous forme API. Celui ci est traité par le backend pour l'id du résultat est retourné dans la fonction de callback. 

![image](https://github.com/user-attachments/assets/e22128a5-62bc-4bb7-81a3-f585c6790421)


La fonction **getResult**, envoie une requête en mode **GET** à l'API **`/polycalculator/id/:id`** du backend en passant comme paramètre l'id du résultat attendu. Une fois reçu, le résultat est affiché sur l'écran de la calculatrice.


# Docker
Voici les différentes commandes pour build et push les images docker
## Backend
docker build . -t europe-west1-docker.pkg.dev/polytech-dijon/polytech-dijon/backend-mh:hubert

docker push europe-west1-docker.pkg.dev/polytech-dijon/polytech-dijon/backend-mh:hubert
## Frontend
docker build . -t europe-west1-docker.pkg.dev/polytech-dijon/polytech-dijon/frontend-mh:hubert

docker push europe-west1-docker.pkg.dev/polytech-dijon/polytech-dijon/frontend-mh:hubert
## Consumer
docker build . -t europe-west1-docker.pkg.dev/polytech-dijon/polytech-dijon/consumer-mh:hubert 

docker push europe-west1-docker.pkg.dev/polytech-dijon/polytech-dijon/consumer-mh:hubert 
