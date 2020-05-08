package mg.sygatechnology.api.app.services;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class ExampleMockService {

    protected static JsonArray data = new JsonArray(
            "[{\"id\": 1, \"name\": \"Jhon\"}, {\"id\": 2, \"name\": \"Doe\"}, {\"id\": 3, \"name\": \"Aaron\"}, {\"id\": 4, \"name\": \"Matthew\"}]"
    );

    public static JsonArray getAll() {
        return data;
    }

    public static JsonObject getByIndex(int index) {
        return data.getJsonObject(index);
    }

    public static void add(JsonObject json) {
        data.add(json);
    }

    public static void set(int index, JsonObject json) {
        data.set(index, json);
    }

    public static void del(int index) {
        data.remove(index);
    }

}
