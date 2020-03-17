package mg.sygatechnology.vertx.system;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import mg.sygatechnology.vertx.app.http.HttpParams;
import mg.sygatechnology.vertx.app.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Controller extends HttpResponse {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private RoutingContext routingC;

    public void initHandler(RoutingContext ctx) {

        this.routingC = ctx;
        setHttpResponse(ctx.response());

        HttpMethod method = request().method();
        String methodName = null;
        if(method.equals(HttpMethod.GET)){
            methodName = "find";
        }
        if(method.equals(HttpMethod.POST)){
            methodName = "create";
        }
        if(method.equals(HttpMethod.PUT)){
            methodName = "update";
        }
        if(method.equals(HttpMethod.DELETE)){
            methodName = "delete";
        }
        String path = request().path().substring(1);
        String[] segment = path.split("\\/");
        int segmentLength = segment.length;
        try {
            int totalParmas = (segmentLength > 0) ? (segment.length - 1) : segmentLength;
            Class paramTypes[] = new Class[totalParmas];
            Object argList[] = new Object[totalParmas];
            int s = 1;
            for(int i = 0; i < totalParmas; i++){
                paramTypes[i] = String.class;
                argList[i] = segment[s];
                s++;
            }
            try {
                Class thisClass = Class.forName(this.getClass().getName());
                Method callbackMethod = thisClass.getDeclaredMethod(methodName, paramTypes);
                callbackMethod.invoke(this, argList);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LOGGER.error(e);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
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
