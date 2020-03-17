package mg.sygatechnology.vertx.configs;

import mg.sygatechnology.vertx.app.controllers.ExampleController;
import mg.sygatechnology.vertx.system.Common;

public class App extends Common {

    public static void initControllers() {

        registerResource("/example", ExampleController.class)
                .registerGetHttpMethod("/example/:index");

    }

}
