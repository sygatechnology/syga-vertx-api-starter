package mg.sygatechnology.vertx.system;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import mg.sygatechnology.vertx.app.http.HttpParams;
import mg.sygatechnology.vertx.app.http.HttpResponse;

public abstract class Controller extends HttpResponse {

    private RoutingContext routingC;


    public void initHandler(RoutingContext rc) {
        this.routingC = rc;
        setHttpResponse(rc.response());

        HttpMethod method = request().method();
        /*String path = request().path().substring(1);

        String[] segment = path.split("\\/");
        if(segment.length == 1) {
            if(method.equals(HttpMethod.GET)){
                find();
            }
            if(method.equals(HttpMethod.POST)){
                create();
            }
            if(method.equals(HttpMethod.PUT)){
                update();
            }
            if(method.equals(HttpMethod.DELETE)){
                delete();
            }
        } else {
            if(method.equals(HttpMethod.GET)){
                find(segment);
            }
        }*/
        if(method.equals(HttpMethod.GET)){
            find();
        }
        if(method.equals(HttpMethod.POST)){
            create();
        }
        if(method.equals(HttpMethod.PUT)){
            update();
        }
        if(method.equals(HttpMethod.DELETE)){
            delete();
        }
    }

    public abstract void find();

    public abstract void create();

    public abstract void update();

    public abstract void delete();

    public HttpParams params() {
        HttpServerRequest request = this.routingC.request();
        return new HttpParams(request.params());
    }

    public Buffer body() {
        return this.routingC.getBody();
    }

    public JsonObject bodyAsObject() {
        return this.routingC.getBodyAsJson();
    }

    public JsonArray bodyAsArray() {
        return this.routingC.getBodyAsJsonArray();
    }

    public String bodyAsString() {
        return this.routingC.getBodyAsString();
    }

    public String bodyAsString(String s) {
        return this.routingC.getBodyAsString(s);
    }

    public RoutingContext routingContext() {
        return this.routingC;
    }

    public HttpServerRequest request() {
        return this.routingC.request();
    }

}
