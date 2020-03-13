package mg.sygatechnology.vertx.system;

import io.vertx.ext.web.RoutingContext;

public abstract class Controller {

    public abstract void find(RoutingContext routingContext);

    public abstract void create(RoutingContext routingContext);

    public abstract void update(RoutingContext routingContext);

    public abstract void delete(RoutingContext routingContext);

    public abstract void resource(RoutingContext routingContext);

}
