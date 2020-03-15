package mg.sygatechnology.vertx.configs;

import io.vertx.ext.web.handler.BodyHandler;
import mg.sygatechnology.vertx.system.Common;
import mg.sygatechnology.vertx.system.Controller;
import mg.sygatechnology.vertx.system.interfaces.RouterInt;

public class Router implements RouterInt {

    protected Controller controller;
    protected io.vertx.ext.web.Router router;

    public Router(Controller c) {
        this.controller = c;
        this.router = Common.getRouter();
    }

    @Override
    public void registerGetHttpMethod(String path) {
        this.router
                .get(path)
                .produces("application/json")
                .handler(this.controller::find);
    }

    @Override
    public void registerPostHttpMethod(String path) {
        this.router
                .post(path)
                .consumes("application/*")
                .produces("application/json")
                .handler(BodyHandler.create())
                .handler(this.controller::create);
    }

    @Override
    public void registerPutHttpMethod(String path) {
        this.router
                .put(path)
                .consumes("application/*")
                .produces("application/json")
                .handler(BodyHandler.create())
                .handler(this.controller::update);
    }

    @Override
    public void registerDeleteHttpMethod(String path) {
        this.router
                .delete(path)
                .produces("application/json")
                .handler(this.controller::delete);
    }
}
