package mg.sygatechnology.api.app.http;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class HttpParams {

    protected MultiMap params;

    public HttpParams(MultiMap p){
        this.params = p;
    }

    public String getString(String key) {
        return this.params.get(key);
    }

    public int getInteger(String key) {
        return Integer.parseInt(getString(key));
    }

    public double getLong(String key) {
        return Long.parseLong(getString(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(getString(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public JsonObject getJsonObject(String key) {
        String stringValue = getString(key);
        if(stringValue.startsWith("{") && stringValue.endsWith("}")) {
            return new JsonObject(stringValue);
        }
        throw new IllegalArgumentException("Invalid json object format");
    }

    public JsonArray getJsonArray(String key) {
        String stringValue = getString(key);
        if(stringValue.startsWith("[") && stringValue.endsWith("]")) {
            return new JsonArray(stringValue);
        }
        throw new IllegalArgumentException("Invalid json array format");
    }

}
