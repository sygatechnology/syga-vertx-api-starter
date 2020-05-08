package mg.sygatechnology.api.app.http;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import mg.sygatechnology.api.system.http.HttpResponseAbstract;

public class HttpResponse extends HttpResponseAbstract {

    private HttpServerResponse httpResponse;

    protected void setHttpResponse(HttpServerResponse hhttpRsp) {
        HttpServerResponse response = hhttpRsp
                .putHeader("Access-Control-Allow-Origin", "*")
                .putHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"
                ).putHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .putHeader("content-type", "application/json; charset=utf-8")
                .setChunked(true);
        this.httpResponse = response;
    }

    @Override
    public void respond(JsonObject data) {
        this.httpResponse
                .setStatusCode(200)
                .setStatusMessage("OK");
        end(data);
    }

    @Override
    public void respond(JsonArray data) {
        this.httpResponse
                .setStatusCode(200)
                .setStatusMessage("OK");
        end(data);
    }

    @Override
    public void respond(JsonObject data, int status, String message) {
        this.httpResponse
                .setStatusCode(status)
                .setStatusMessage(message);
        end(data);
    }

    @Override
    public void respond(JsonArray data, int status, String message) {
        this.httpResponse
                .setStatusCode(status)
                .setStatusMessage(message);
        end(data);
    }

    @Override
    public void respondCreated() {
        this.httpResponse
                .setStatusCode(201)
                .setStatusMessage("Created")
                .end();
    }

    @Override
    public void respondCreated(String message) {
        this.httpResponse
                .setStatusCode(201)
                .setStatusMessage(message)
                .end();
    }

    @Override
    public void respondCreated(JsonObject data, String message) {
        this.httpResponse
                .setStatusCode(201)
                .setStatusMessage(message);
        end(data);
    }

    @Override
    public void respondCreated(JsonArray data, String message) {
        this.httpResponse
                .setStatusCode(201)
                .setStatusMessage(message);
        end(data);
    }

    @Override
    public void fail(String errorMessage) {
        this.httpResponse
                .setStatusCode(400)
                .setStatusMessage("Error");
        JsonObject data = new JsonObject()
                .put("status", 400)
                .put("error", 400)
                .put("messages", new JsonArray().add(errorMessage));
        end(data);
    }

    @Override
    public void fail(String errorMessage, int status, int code, String customMessage) {
        this.httpResponse
                .setStatusCode(status)
                .setStatusMessage(customMessage);
        JsonObject data = new JsonObject()
                .put("status", status)
                .put("error", code)
                .put("messages", new JsonArray().add(errorMessage));
        end(data);
    }

    public void fail(JsonArray errorMessages, int status, int code, String customMessage) {
        this.httpResponse
                .setStatusCode(status)
                .setStatusMessage(customMessage);
        JsonObject data = new JsonObject()
                .put("status", status)
                .put("error", code)
                .put("messages", errorMessages);
        end(data);
    }

    private void end(JsonObject data) {
        this.httpResponse.end(Json.encodePrettily(data));
    }

    private void end(JsonArray data) {
        this.httpResponse.end(Json.encodePrettily(data));
    }

}
