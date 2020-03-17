package mg.sygatechnology.vertx.app.controllers;

import io.vertx.core.json.JsonObject;
import mg.sygatechnology.vertx.app.services.ExampleMockService;
import mg.sygatechnology.vertx.system.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleController extends Controller {

    private static final Logger LOGGER = LogManager.getLogger(ExampleController.class);

    @Override
    public void find() {
        LOGGER.info("GET request");
        respond(ExampleMockService.getAll());
    }

    @Override
    public void create() {
        LOGGER.info("POST request");
        ExampleMockService.add(bodyAsObject());
        respondCreated("Example object created");
    }

    @Override
    public void update() {
        LOGGER.info("UPDATE request");
        JsonObject json = bodyAsObject();
        int index = json.getInteger("index");
        JsonObject obj = json.getJsonObject("obj");
        ExampleMockService.set(index, obj);
        respond(json);
    }

    @Override
    public void delete() {
        LOGGER.info("DELETE request");
        JsonObject json = bodyAsObject();
        int index = json.getInteger("index");
        ExampleMockService.del(index);
        respond(ExampleMockService.getAll());
    }
}
