# Projet API Spring Boot + GraphQL

## API
- Lancement de l'api lors de l'éxecution du projet sur le port 8080
- Accès graphiql : http://localhost:8080/graphiql
- L'API expose les routes basique du CRUD pour les entités Player et Team.

## Test graphiql
Il est possible de tester l'API dirrectement sur http://localhost:8080/graphiql en écrivant des query et des mutations

## Test NodeJS
- le script index.js permet de lancer une série de requête à l'aide du package graphql-request. Ces requêtes couvrent presques toutes les routes disponibles de l'API. Le script éxécute les requêtes du scénario suivant : 
    - liste tous les joueurs
    - crée un nouveau joueur
    - met à jour ce nouveau joueur
    - affiche l'équipe du nouveau joueur
    - crée une nouvelle équipe [#10] avec 2 joueurs d'une autre équipe [#0]
    - affiche l'équipe [#0] et ses joueurs (plus de joueurs car ils sont passé dans l'équipe [#10])
    - met à jour la nouvelle équipe
    - supprime le nouveau joueur 
    - supprime la nouvelle équipe 
    - liste tous les joueurs
    - liste toutes les équipes

- lancement du script :

>placement dans le dossier  
>
`cd js`  

>installation des dépendances  
>
`npm i`  

>execution  
>
`npm run-script test-api`  

>execution bis  
>
`node ./index.js`  