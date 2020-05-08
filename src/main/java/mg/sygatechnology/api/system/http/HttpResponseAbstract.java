package mg.sygatechnology.api.system.http;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public abstract class HttpResponseAbstract {

    public abstract void respond(JsonObject data);

    public abstract void respond(JsonArray data);

    public abstract void respond(JsonObject data, int status, String message);

    public abstract void respond(JsonArray data, int status, String message);

    public abstract void respondCreated();

    public abstract void respondCreated(String message);

    public abstract void respondCreated(JsonObject data, String message);

    public abstract void respondCreated(JsonArray data, String message);

    public abstract void fail(String messages);

    public abstract void fail(String messages, int status, int code, String customMessage);

    public abstract void fail(JsonArray errorMessages, int status, int code, String customMessage);

}
