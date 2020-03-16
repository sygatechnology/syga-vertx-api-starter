package mg.sygatechnology.vertx.app.controllers;

import mg.sygatechnology.vertx.system.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleController extends Controller {

    private static final Logger LOGGER = LogManager.getLogger(ExampleController.class);

    @Override
    public void find() {
        LOGGER.info("Mihinan'ny ampangony eeeee");
        System.out.println("Path " + request().path().substring(1));
    }

    @Override
    public void create() {
        System.out.println("Mety Create letsy e !!!!");
        System.out.println("Method " + request().method().name());
    }

    @Override
    public void update() {
        System.out.println("Mety Update letsy e !!!!");
        System.out.println("Method " + request().method().name());
    }

    @Override
    public void delete() {
        System.out.println("Mety Delete letsy e !!!!");
        System.out.println("Method " + request().method().name());
    }
}
