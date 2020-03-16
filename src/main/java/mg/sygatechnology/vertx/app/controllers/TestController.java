package mg.sygatechnology.vertx.app.controllers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import mg.sygatechnology.vertx.system.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestController extends Controller {

    private static final Logger LOGGER = LogManager.getLogger(TestController.class);

    @Override
    public void find() {
        LOGGER.info("Tatay eeeeee");
        respond(new JsonArray()
                .add(new JsonObject()
                        .put("id", 1)
                )
                .add(new JsonObject()
                        .put("id", 2)
                )
                .add(new JsonObject()
                        .put("id", 3)
                )
        );
    }

    @Override
    public void create() {
        LOGGER.info("Tatay 2222222222 eeeeee");
        respondCreated();
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
