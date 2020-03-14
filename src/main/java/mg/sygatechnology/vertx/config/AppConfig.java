package mg.sygatechnology.vertx.config;

import mg.sygatechnology.vertx.app.controllers.LogController;
import mg.sygatechnology.vertx.system.Common;

public class AppConfig extends Common {

    public static void init() {

        registerResource("/test", new LogController(), "get", "post");

    }

}
