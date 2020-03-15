package mg.sygatechnology.vertx.configs;

import mg.sygatechnology.vertx.app.controllers.LogController;
import mg.sygatechnology.vertx.system.Common;

public class AppConfig extends Common {

    public static void initControllers() {

        registerResource("/test", new LogController());

    }

}
