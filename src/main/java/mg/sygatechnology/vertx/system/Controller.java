package mg.sygatechnology.vertx.system;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import mg.sygatechnology.vertx.system.Common;

import io.vertx.ext.web.handler.BodyHandler;


public abstract class Controller {

    protected String routeGetMethod;

    protected String routePostMethod;

    protected String routePutMethod;

    protected String routeDeleteMethod;

    public abstract void find(RoutingContext routingContext);

    public abstract void create(RoutingContext routingContext);

    public abstract void update(RoutingContext routingContext);

    public abstract void delete(RoutingContext routingContext);

    public abstract void resource(RoutingContext routingContext);

}
