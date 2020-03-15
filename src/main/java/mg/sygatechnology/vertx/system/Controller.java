package mg.sygatechnology.vertx.system;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import mg.sygatechnology.vertx.app.http.HttpParams;

public abstract class Controller {

    /*public Controller() {
        System.out.println("Voaantso valohany aho eee");
    }*/

    protected RoutingContext routingContext;

    public abstract void find(RoutingContext rc);

    public abstract void create(RoutingContext rc);

    public abstract void update(RoutingContext rc);

    public abstract void delete(RoutingContext rc);

    public abstract void resource(RoutingContext rc);

    public HttpParams params(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        return new HttpParams(request.params());
    }

}
