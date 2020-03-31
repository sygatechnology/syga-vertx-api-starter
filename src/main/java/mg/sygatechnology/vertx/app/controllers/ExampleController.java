package mg.sygatechnology.vertx.app.controllers;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import mg.sygatechnology.vertx.app.services.ExampleMockService;
import mg.sygatechnology.vertx.system.Controller;
import mg.sygatechnology.vertx.system.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
