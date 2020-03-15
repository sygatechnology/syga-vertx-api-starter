package mg.sygatechnology.vertx.configs;

import io.vertx.ext.web.handler.BodyHandler;
import mg.sygatechnology.vertx.system.Common;
import mg.sygatechnology.vertx.system.Controller;
import mg.sygatechnology.vertx.system.interfaces.RouterInt;

public class Router implements RouterInt {

    protected Controller controller;

    public Router(Controller c) {
        this.controller = c;
    }

    @Override
    public void registerGetHttpMethod(String path) {
        Common.getRouter()
                .get(path)
                .produces("application/json")
                .handler(this.controller::find);
    }

    @Override
    public void registerPostHttpMethod(String path) {
        Common.getRouter().post(path)
                .consumes("application/*")
                .produces("application/json")
                .handler(BodyHandler.create())
                .handler(this.controller::create);
    }

    @Override
    public void registerPutHttpMethod(String path) {
        Common.getRouter().put(path)
                .consumes("application/*")
                .produces("application/json")
                .handler(BodyHandler.create())
                .handler(this.controller::update);
    }

    @Override
    public void registerDeleteHttpMethod(String path) {
        Common.getRouter()
                .delete(path)
                .produces("application/json")
                .handler(this.controller::delete);
    }
}
