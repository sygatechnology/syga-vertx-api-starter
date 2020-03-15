package mg.sygatechnology.vertx.configs;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigItem {

    protected Properties properties;

    /**
     * Constructor
     */
    public ConfigItem(Properties prop) {
        this.properties = prop;
    }

    public String getString(String key) {
        return this.properties.getProperty(key);
    }

    public int getInteger(String key) {
        return Integer.parseInt(getString(key));
    }

    public long getLong(String key) {
        return Long.parseLong(getString(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(getString(key));
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

    public Object getObject(String key) {
        return this.properties.get(key);
    }

    public ConfigItem getGroup(String prefix) {
        Properties prop = new Properties();
        prefix = prefix+".";
        List<String> keys = keyNames();
        for(String key : keys) {
            if(key.startsWith(prefix)) {
                prop.setProperty(key.substring(prefix.length()), getString(key));
            }
        }
        return new ConfigItem(prop);
    }

    public List<String> keyNames() {
        List<String> keys = new ArrayList<>();
        for(Object key : this.properties.keySet()) {
            keys.add(key.toString());
        }
        return keys;
    }

    public boolean keyExists(String key) {
        return this.properties.containsKey(key);
    }

}
