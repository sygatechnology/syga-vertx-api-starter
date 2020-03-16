package mg.sygatechnology.vertx.system;

import io.vertx.core.Vertx;
import mg.sygatechnology.vertx.configs.Config;
import mg.sygatechnology.vertx.configs.ConfigItem;
import mg.sygatechnology.vertx.system.http.Router;
import mg.sygatechnology.vertx.system.http.SubRouter;

import java.util.HashMap;
import java.util.Map;

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
    public static SubRouter registerRoute(String httpMethod, String path, Controller controller) {
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
        } else {
            throw new IllegalArgumentException("Http method " + httpMethod + " not allowed.");
        }
        return new SubRouter(router);
    }

    /**
     * Register Resource
     */
    public static SubRouter registerResource(String path, Controller controller, String... onlyMethods) {
        Map<String, Boolean> routes = new HashMap();
        if(onlyMethods.length == 0) {
            routes.put("get", true);
            routes.put("post", true);
            routes.put("put", true);
            routes.put("delete", true);
        } else {
            for (String httpMethod : onlyMethods){
                httpMethod = httpMethod.toLowerCase();
                routes.put(httpMethod, true);
            }
        }
        SubRouter subRouter = null;
        for (Map.Entry<String, Boolean> entry : routes.entrySet()) {
            if (entry.getValue() == true) {
                subRouter = registerRoute(entry.getKey(), path, controller);
            }
        }
        if(subRouter == null) {
            throw new IllegalArgumentException("Router resource must have at least one http method ( GET, POST, PUT or DELETE )");
        }
        return subRouter;
    }
}
