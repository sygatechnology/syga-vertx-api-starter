package mg.sygatechnology.vertx.app.controllers;

import io.vertx.ext.web.RoutingContext;
import mg.sygatechnology.vertx.system.Controller;

public class LogController extends Controller {

    @Override
    public void find(RoutingContext routingContext) {
    }

    @Override
    public void create(RoutingContext routingContext) {
        System.out.println("Mety Create letsy e !!!!");
    }

    @Override
    public void update(RoutingContext routingContext) {
        System.out.println("Mety Update letsy e !!!!");
    }

    @Override
    public void delete(RoutingContext routingContext) {
        System.out.println("Mety Delete letsy e !!!!");
    }

    @Override
    public void resource(RoutingContext routingContext) {

    }
}
