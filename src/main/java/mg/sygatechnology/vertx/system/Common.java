package mg.sygatechnology.vertx.system;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import mg.sygatechnology.vertx.configs.Router;
import mg.sygatechnology.vertx.configs.Config;
import mg.sygatechnology.vertx.configs.ConfigItem;

public class Common {

    protected static io.vertx.ext.web.Router router;
    protected static Vertx vertx;

    /**
     * Init Router
     */
    public static void initRouter(Vertx v) {
        vertx = v;
        router = io.vertx.ext.web.Router.router(vertx);
    }

    /**
     * Get Router
     */
    public static io.vertx.ext.web.Router getRouter() {
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
     * Register Route
     */
    public static void registerRoute(String httpMethod, String path, Controller controller) {
        Router router = new Router(controller);
        httpMethod = httpMethod.toLowerCase();
        if(! path.startsWith("/")) {
            path = "/"+path;
        }
        if(httpMethod.equals("get")) {
            router.registerGetHttpMethod(path);
        } else if(httpMethod.equals("post")) {
            router.registerPostHttpMethod(path);
        } else if(httpMethod.equals("put")) {
            router.registerPutHttpMethod(path);
        } else if(httpMethod.equals("delete")) {
            router.registerDeleteHttpMethod(path);
        }
    }

    /**
     * Register Resource
     */
    public static void registerResource(String path, Controller controller, String... excludedMethods) {
        JsonObject routes = new JsonObject()
                                    .put("get", true)
                                    .put("post", true)
                                    .put("put", true)
                                    .put("delete", true);
        if(excludedMethods.length > 0) {
            for (String httpMethod : excludedMethods){
                httpMethod = httpMethod.toLowerCase();
                routes.put(httpMethod, false);
            }
        }
        routes.forEach(entry -> {
            if ((Boolean) entry.getValue() == true) {
                registerRoute(entry.getKey(), path, controller);
            }
        });
    }
}
