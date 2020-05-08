package mg.sygatechnology.api.system.interfaces;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

public interface RequestInt {

    void set(RoutingContext routingContext);

    HttpServerRequest get();

}
