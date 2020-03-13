package mg.sygatechnology.vertx.system;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import mg.sygatechnology.vertx.system.config.Config;
import mg.sygatechnology.vertx.system.config.ConfigItem;


public class Common {

    protected static Router router;

    /**
     * Init Router
     */

    public static void initRouter(Vertx vertx) {
        router = Router.router(vertx);
    }



    public static Router getRouter() {
        return router;
    }

    /**
     * Register Config
     */
    public static ConfigItem registerConfig(String name, String path) {
        return Config.get(name, false, path);
    }

    /**
     * Get Shared Config
     */
    public static ConfigItem config(String environment) {
        return Config.get(environment, true);
    }

    /**
     * Get Config
     */
    public static ConfigItem config(String environment, boolean getShared) {
        return Config.get(environment, getShared);
    }

    /**
     * Register Controllers
     */
    public static void registerControllers() {

    }

    /**
     * Register Route
     */
    public static void registerRoute() {

    }

}
