package mg.sygatechnology.api.system.http;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import mg.sygatechnology.api.system.Common;
import mg.sygatechnology.api.system.Controller;
import mg.sygatechnology.api.system.interfaces.RouterInt;

public class Router implements RouterInt {

    protected Controller controller;
    protected io.vertx.ext.web.Router router;
    protected String controllerMethod;

    public Router(Controller c) {
        this.controller = c;
        this.router = Common.getRouter();

        this.router.route().failureHandler(ctx -> {
            if (ctx.statusCode() == 404) {
                ctx.response()
                        .setStatusCode(404)
                        .end("NOT FOUND fancy html here!!!");
            } else {
                ctx.next();
            }
        });
    }

    @Override
    public void registerGetHttpMethod(String path, String cMethod, String produces) {
        this.controllerMethod = cMethod;
        this.router
                .get(path)
                .produces(produces)
                .handler(this::setHandlerCallback);
    }

    @Override
    public void registerPostHttpMethod(String path, String cMethod, String produces, String consumes) {
        this.controllerMethod = cMethod;
        this.router
                .post(path)
                .consumes(consumes)
                .produces(produces)
                .handler(BodyHandler.create())
                .handler(this::setHandlerCallback);
    }

    @Override
    public void registerPutHttpMethod(String path, String cMethod, String produces, String consumes) {
        this.controllerMethod = cMethod;
        this.router
                .put(path)
                .consumes(consumes)
                .produces(produces)
                .handler(BodyHandler.create())
                .handler(this::setHandlerCallback);
    }

    @Override
    public void registerDeleteHttpMethod(String path, String cMethod, String produces, String consumes) {
        this.controllerMethod = cMethod;
        this.router
                .delete(path)
                .consumes(consumes)
                .produces(produces)
                .handler(BodyHandler.create())
                .handler(this::setHandlerCallback);
    }

    private void setHandlerCallback(RoutingContext cxt) {
        this.controller.initHandler(cxt, this.controllerMethod);
    }
}
