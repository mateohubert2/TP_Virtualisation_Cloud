# Backend:
## App.java:
![image](https://github.com/user-attachments/assets/e87d2b2d-dac3-4e2d-bcef-ea0c1af06fe2)

Le fichier **App.java** contient la déclaration du webserver utilisé pour faire fonctionner les API. Le port d'écoute du webserver est le **port 8080**. L'ensemble des fichiers du dossier webserver sont les fichiers du projet [PolyNames](https://github.com/mateohubert2/PolyNames). 
### API:
**`http://localhost:8080/polycalculator/id/:id`** Cette API se utilise la méthode **GET**. Il faut fournir l'id d'un calcul qui a été réalisé afin d'en récupérer le résultat. Cette API appelle la fonction [GetCalculResult](##GetCalculResult) de [CalculController.java](#CalculController.java.).

**`http://localhost:8080/polycalculator/calcul/:calcul`** Cette API se utilise la méthode **POST**. Il faut fournir le calcul à réaliser en respectant les correspondances ci-dessous:
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
![image](https://github.com/user-attachments/assets/16518a7c-b7af-43ec-8652-64e00f1b320d)

La fonction CalculProcess créée une **request** qui correspond au context qui fait la requête sur l'API ainsi qu'une **response** pour pouvoir répondre après le traitement de la requête. Dans un premier temps, on récupère le calcul en tant que **String** dans **calculString**. Ensuite, on remplace tout les mots par leur symbole. Dans le calcul transmit à l'API, 3 opérateurs sont des mots car sinon il y a une erreur de route lors de l'envoi. Ensuite, on utilise le **ScriptEngine JavaScript** pour évaluer l'expression mathématiques le tout en respectant les priorités opératoires. Le résultat est ensuite stocké dans un tableau de résultat. L'id du résultat est renvoyé comme réponse.

## GetCalculResult:
![image](https://github.com/user-attachments/assets/b7add1bd-8e14-4548-b918-7d9397378ffc)

La fonction GetCalculResult créée une **request** qui correspond au context qui fait la requête sur l'API ainsi qu'une **response** pour pouvoir répondre après le traitement de la requête. Dans un premier temps, on récupère l'id de la requête. On le décrémente de 1 car un tableau commence à 0 puis on envoie le résultat du calcul souhaité comme réponse.

# Frontend:
![image](https://github.com/user-attachments/assets/d1476aa2-cf1a-4ab6-a075-164dd739a5b8)

## index.html:
Le fichier index.html est extrêmement simple. Il y a les différents boutons de créés, chacun avec un id pour pouvoir déclarer des EventListener dessus. 

## style.css:
Le fichier style.css permet d'avoir une coloration des différents boutons et de la calculatrice en général.

## script.js:
![image](https://github.com/user-attachments/assets/4392d652-1017-4c71-a9de-7166776bc790)

Le fichier script.js permet quand à lui de faire fonctionner la calculatrice avec le backend. Pour chaque bouton, un  EventListener sur le click est déclaré. On regarde dans un premier temps si le bouton est appuyé juste après avoir reçu un résultat pour éviter d'avoir un problème d'affiche de type: "résultat"1+2 mais bien 1+2.

Ensuite, la variable calcul représente le calcul à envoyer à l'API. Elle est donc de la forme: "1+parl2div4pard*3". La variable calculDisplay est quand à elle destinée à être affichée sur l'écran de la calculatrice. Elle est donc de la forme: 1+(2/4)*3.

![image](https://github.com/user-attachments/assets/5cb8a84c-fdc9-4835-9157-2e62c6aa88c5)

Un bouton particulier est le bouton DEL. En effet, quand il faut supprimer le dernier élément ajouté, il y a une nuance entre calcul et calculDisplay. Pour calculDisplay, il suffit de supprimer le dernier charactère avec **calculDisplay  =  calculDisplay.slice(0, -1)**. Cependant pour calcul qui est envoyé à l'API, un **/** étant **div** et une **(** etant **parl ou pard**, il faut supprimer entre 3 et 4 charactères pour avoir un calcul correct. La variable **lastKey** permet donc de savoir la dernière touche à avoir été appuyée pour savoir si il faut supprimer 1, 3 ou 4 charactères.

![image](https://github.com/user-attachments/assets/ebe7bcdf-cec4-412a-8103-cbcc60acc469)

A chaque fois qu'une touche est appuyée, la fonction **displayResult** est appelée. Elle permet simplement de mettre à jour l'affichage de la calculatrice.

![image](https://github.com/user-attachments/assets/00687ac7-fdab-4b81-8c2a-9b2a5dd9917e)

Le dernier bouton important est le bouton **=**. Quand on clique dessus, on fait appel dans un premier temps à [sendCalcul](##sendCalcul) de [CalculService.java](##CalculService.java) puis [getResult](##getResult) de [CalculService.java](##CalculService.java) .

## CalculService.java:

![image](https://github.com/user-attachments/assets/566e89c1-069f-4ce0-9c1c-571f438d2051)

La fonction **sendCalcul**, envoie une requête en mode **POST** à l'API **`http://localhost:8080/polycalculator/calcul/:calcul`** du backend en passant comme paramètre le calcul sous forme API. Celui ci est traité par le backend pour l'id du résultat est retourné dans la fonction de callback. 

![image](https://github.com/user-attachments/assets/192d298d-8ccb-4140-8d4a-34d969fc3416)

La fonction **getResult**, envoie une requête en mode **GET** à l'API **`http://localhost:8080/polycalculator/id/:id`** du backend en passant comme paramètre l'id du résultat attendu. Une fois reçu, le résultat est affiché sur l'écran de la calculatrice.
