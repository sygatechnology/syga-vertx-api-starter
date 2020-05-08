package mg.sygatechnology.api.system;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import mg.sygatechnology.api.app.helpers.CommonHelper;
import mg.sygatechnology.api.configs.Config;
import mg.sygatechnology.api.configs.ConfigItem;
import mg.sygatechnology.api.system.annotations.*;
import mg.sygatechnology.api.system.http.Router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public static void initControllers() {
        for(Class controller : CommonHelper.getControllerClasses()){
            Method[] methods = controller.getDeclaredMethods();
            for ( Method m : methods ) {
                Route route = m.getAnnotation( Route.class );
                Produces produces = m.getAnnotation( Produces.class );
                Consumes consumes = m.getAnnotation( Consumes.class );
                mg.sygatechnology.api.system.annotations.Method method = m.getAnnotation( mg.sygatechnology.api.system.annotations.Method.class );
                if ( route != null && method != null ) {
                    String methodName = m.getName();
                    registerRoute(method.value(), route.value(), controller, methodName, produces.value(), consumes.value());
                }
            }
        }
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
    public static ConfigItem config(String name) {
        return Config.get(name, true);
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
    public static void registerRoute(HttpMethod httpMethod, String path, Class controllerClass, String controllerMethodName, String produces, String consumes) {
        try {
            Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
            Router router = new Router(controller);
            if(! path.startsWith("/")) {
                path = "/"+path;
            }
            if(httpMethod.equals(HttpMethod.GET)) {
                router.registerGetHttpMethod(path, controllerMethodName, produces);
            } else if(httpMethod.equals(HttpMethod.POST)) {
                router.registerPostHttpMethod(path, controllerMethodName, produces, consumes);
            } else if(httpMethod.equals(HttpMethod.PUT)) {
                router.registerPutHttpMethod(path, controllerMethodName, produces, consumes);
            } else if(httpMethod.equals(HttpMethod.DELETE)) {
                router.registerDeleteHttpMethod(path, controllerMethodName, produces, consumes);
            } else {
                throw new IllegalArgumentException("Http method " + httpMethod + " not allowed.");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException("Http method " + httpMethod + " not allowed.");
        }

        //return new SubRouter(router);
    }

    /**
     * Register Resource
     */
    /*public static SubRouter registerResource(String path, Class controllerClass, String... onlyMethods) {
        try {
            Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
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
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Error");
    }*/
}
