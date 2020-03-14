package mg.sygatechnology.vertx.system;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import mg.sygatechnology.vertx.helpers.FileHelper;
import mg.sygatechnology.vertx.system.config.Config;
import mg.sygatechnology.vertx.system.config.ConfigItem;
import mg.sygatechnology.vertx.system.impl.RouterImpl;

import java.util.List;


public class Common {

    protected static Router router;

    /**
     * Init App
     */

    public static void initApp(Vertx vertx) {
        router = Router.router(vertx);
    }

    /*private static void initAppControllers() {
        List<String> controllers = FileHelper.getFilesWithoutExt("./src/main/java/mg/sygatechnology/vertx/app/controllers");
        String controllersPackage = "mg.sygatechnology.vertx.app.controllers";
        for (String c : controllers) {
            try {
                Class cls = Class.forName(controllersPackage+"."+c);
                Controller controller = (Controller) cls.newInstance();
                controller.setGetHttpMethod(controller.routeGetMethod);

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * Get Router
     */
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
     * Register Route
     */
    public static void registerRoute(String httpMethod, String path, Controller controller) {
        RouterImpl routerImpl = new RouterImpl(controller);
        if(httpMethod.equals("get")) {
            routerImpl.registerGetHttpMethod(path);
        } else if(httpMethod.equals("post")) {
            routerImpl.registerPostHttpMethod(path);
        } else if(httpMethod.equals("put")) {
            routerImpl.registerPutHttpMethod(path);
        } else if(httpMethod.equals("delete")) {
            routerImpl.registerDeleteHttpMethod(path);
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
            for (String m : excludedMethods){
                routes.put(m, false);
            }
        }
        routes.forEach(entry -> {
            if ((Boolean) entry.getValue() == true) {
                registerRoute(entry.getKey(), path, controller);
            }
        });
    }
}
