# Syga Vertx Rest API Starter

![Java CI with Maven](https://github.com/sygatechnology/vertx-api-starter/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

## Qu'est-ce que Syga Vertx Rest API Starter?

Syga Vertx Rest API Starter est un outil pour créer un API Rest, développé à partir de la trousse d'outils pour créer des applications réactives sur la JVM, Eclipse Vert.x, utilisant le langage JAVA .

Plus d'informations peuvent être trouvées sur le [site officiel](https://vertx.io).

**Ceci est encore une version de test, mais vous pouvez bien l'améliorer à votre guise ;)**

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

Enregistrement de resource (GET, POST, PUT et DELETE) et son contrôleur

* Créez un contrôleur dans le package `controllers` qui hérite la classe abstraite `Controller` dans le package `system`

(Vous pouvez voir l'exemple du contrôleur `ExampleController`) 

Vous avez quatre annotations : `@Route`, `@Method`, `@Produces` et `@Consumes` du package `system.annotations`

* `@Route` : pour la déclaration de votre route

* `@Method` : pour la méthode http à utiliser (Prend comme paramètre `HttpMethod` de vertx)

* `@Produces` : type de contenu produit par cette route

* `@Consumes` : type de contenu consommé par cette route

Toutes ces annotations sont utilisable au niveau des méthodes du contrôleur

Pour les réponses, vous avez les méthodes `respond` et `respondCreated` (Voir la classe parente `Controller`)

### Exemples
```java
public class ExampleController extends Controller {

    private static final Logger LOGGER = LogManager.getLogger(ExampleController.class);

    @Route("/example")
    @Method(HttpMethod.GET)
    @Produces("application/json")
    public void find() {
        LOGGER.info("GET request");
        respond(ExampleMockService.getAll());
    }

    @Route("/example/:index")
    @Method(HttpMethod.GET)
    @Produces("application/json")
    public void find(String sIndex) {
        LOGGER.info("GET request with one arg");
        int index = Integer.parseInt(sIndex);
        respond(ExampleMockService.getByIndex(index));
    }

    @Route("/example")
    @Method(HttpMethod.POST)
    @Produces("application/json")
    @Consumes("application/json")
    public void create() {
        LOGGER.info("POST request");
        ExampleMockService.add(bodyAsObject());
        respondCreated("Example object created");
    }

    @Route("/example")
    @Method(HttpMethod.PUT)
    @Produces("application/json")
    @Consumes("application/json")
    public void update() {
        LOGGER.info("UPDATE request");
        JsonObject json = bodyAsObject();
        int index = json.getInteger("index");
        JsonObject obj = json.getJsonObject("data");
        ExampleMockService.set(index, obj);
        respond(json);
    }

    @Route("/example")
    @Method(HttpMethod.DELETE)
    @Produces("application/json")
    @Consumes("application/json")
    public void delete() {
        LOGGER.info("DELETE request");
        JsonObject json = bodyAsObject();
        int index = json.getInteger("index");
        ExampleMockService.del(index);
        respond(ExampleMockService.getAll());
    }
}
```

Et pour tester après avoir lancé l'application
```bash
// GET
curl http://localhost:8081/example

// GET
curl http://localhost:8081/example/1

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

orm, session et autres

#### Syga Vertx Rest API Starter
