package mg.sygatechnology.vertx.configs;

import mg.sygatechnology.vertx.app.controllers.LogController;
import mg.sygatechnology.vertx.app.controllers.TestController;
import mg.sygatechnology.vertx.system.Common;

public class App extends Common {

    public static void initControllers() {

        registerResource("/test", new LogController()).subGetHttpMethod("/test/:param");
        registerResource("/test2", new TestController(), "get", "post");

    }

}
