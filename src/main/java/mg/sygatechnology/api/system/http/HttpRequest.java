package mg.sygatechnology.api.system.http;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import mg.sygatechnology.api.system.interfaces.RequestInt;

public class HttpRequest implements RequestInt {

    protected HttpServerRequest request;

    @Override
    public void set(RoutingContext routingContext) {
        this.request = routingContext.request();
    }

    @Override
    public HttpServerRequest get() {
        return this.request;
    }

}
