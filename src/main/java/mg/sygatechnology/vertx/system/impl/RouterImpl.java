package mg.sygatechnology.vertx.system.impl;

import io.vertx.ext.web.handler.BodyHandler;
import mg.sygatechnology.vertx.system.Common;
import mg.sygatechnology.vertx.system.Controller;
import mg.sygatechnology.vertx.system.interfaces.Router;

public class RouterImpl implements Router {

    protected Controller controller;

    public RouterImpl(Controller c) {
        this.controller = c;
    }

    public void registerGetHttpMethod(String path) {
        Common.getRouter()
                .get(path)
                .produces("application/json")
                .handler(this.controller::find);
    }

    public void registerPostHttpMethod(String path) {
        Common.getRouter().post(path)
                .consumes("application/*")
                .produces("application/json")
                .handler(BodyHandler.create())
                .handler(this.controller::create);
    }

    public void registerPutHttpMethod(String path) {
        Common.getRouter().put(path)
                .consumes("application/*")
                .produces("application/json")
                .handler(BodyHandler.create())
                .handler(this.controller::update);
    }

    public void registerDeleteHttpMethod(String path) {
        Common.getRouter()
                .delete(path)
                .produces("application/json")
                .handler(this.controller::delete);
    }
}
