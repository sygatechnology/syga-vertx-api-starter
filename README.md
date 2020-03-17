# Syga Vertx Rest API Starter

## Qu'est-ce que Syga Vertx Rest API Starter?

Syga Vertx Rest API Starter est un outil pour créer un API Rest, développé à partir de la trousse d'outils pour créer des applications réactives sur la JVM, Eclipse Vert.x, utilisant le langage JAVA .

Plus d'informations peuvent être trouvées sur le [site officiel](https://vertx.io).

**Ceci est encore une version de test**

### Comment ça marche?

Vous avez 3 packages:

* `app`: contient les sous-packages : `controllers`, `entities`, `helpers`, `services` et `http`
    * `controllers`: contient vos controleurs
    * `entities`: contient vos entités
    * `helpers`: contient vos helpers
    * `http`: contient les classes concernant les requêtes HTTP
* `configs`: contient tous les fichiers de configuration
* `system`: contient les sous-packages : `http` et `interfaces`
    * `http`: contient les classes abstraites et les classes parentes
    * `interfaces`: contient les interfaces

La classe `App` dans le package `configs`, c'est le point d'entrée pour la déclaration de vos ressources et routes.

Vous pouvez modifier toutes les valeurs des propiétés par `environnement` dans `resources/environments`

* `development.properties`: pour l'environnement de dév
* `staging.properties`: pour l'environnement de démo
* `production.properties`: pour l'environnement de prod

Le fichier de configuration pour les logs est dans `resources/log4j2.xml`

### Lancement

Sur ce dossier se trouve un fichier `run`:

```bash
./run
```

La class principale est `AppStarter`

### Exemples

Enregistrement de resource (GET, POST, PUT et DELETE) et son contrôleur

* Créez un contrôleur dans le package `controllers` qui hérite la classe abstraite `Controller` dans le package `system`

(Vous pouvez voir l'exemple du contrôleur `ExampleController`) 

* Dans la fonction statique `initControllers()` de la classe `vertx/configs/App`, vous allez déclarer vos routes
```java
    public static void initControllers() {

        registerResource("/example", new ExampleController());
    
        // correspond à
    
        ExampleController exampleController = new ExampleController();
        registerRoute("get", "/example", exampleController);
        registerRoute("post", "/example", exampleController);
        registerRoute("put", "/example", exampleController);
        registerRoute("delete", "/example", exampleController);
    
        // Vous pouvez enregistrer une ressource avec juste les méthodes que vous voulez utiliser comme ceci
    
        registerResource("/example", new ExampleController(), "get", "post");
        // Dans ce cas, votre route n'a accès qu'aux méthodes GET et POST

    }
```

Et pour tester après avoir lancé l'application
```bash
// GET
curl http://localhost:8081/example

// POST
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id": 5, "name": "Chris"}' \
  http://localhost:8081/example

// PUT
curl --header "Content-Type: application/json" \
  --request PUT \
  --data '{"index": 3, "obj": {"id": 4, "name": "William"}}' \
  http://localhost:8081/example

// DELETE
curl --header "Content-Type: application/json" \
  --request DELETE \
  --data '{"index": 2}' \
  http://localhost:8081/example
```

### Fonctionnalités en cours d'implementation

* `sub-route`: pour différencier les appels des fonctions du même nom mais avec des paramètres différents
* `jdbc-builder`: bibliothèque pour les bases de données
* `user-management`: gestion d'utilisateur par défaut
* `jwt`: pour les sessions  

#### Syga Vertx Rest API Starter