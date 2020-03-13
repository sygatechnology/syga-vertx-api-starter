package mg.sygatechnology.vertx.system.config;

import java.awt.geom.IllegalPathStateException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Config {

    /**
     * var @instances Config items container
     */
    private static HashMap<String, ConfigItem> instatances = new HashMap<>();

    public static ConfigItem get(String name) {
        ConfigItem configItem = get(name, false);
        instatances.put(name, configItem);
        return  configItem;
    }

    public static ConfigItem get(String name, boolean getShared) {
        if(getShared && instatances.containsKey(name)){
            return instatances.get(name);
        }
        ConfigItem configItem = new ConfigItem(read(name, "/"));
        instatances.put(name, configItem);
        return  configItem;
    }

    public static ConfigItem get(String name, boolean getShared, String path) {
        if(getShared && instatances.containsKey(name)){
            return instatances.get(name);
        }
        ConfigItem configItem = new ConfigItem(read(name, path));
        instatances.put(name, configItem);
        return  configItem;
    }

    private static Properties read(String name, String path) {
        name = name.trim();
        String initialName = name;
        if(path.startsWith("/")) {
            path = path.substring(1);
        }
        if(path.length() > 1 && ! path.endsWith("/")) {
            path = path+"/";
        }
        name = path+name;
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(name+".properties")) {

            Properties prop = new Properties();

            if (input == null) {
                throw  new InvalidPropertiesFormatException("Sorry, unable to find valid properties format");
            }

            prop.load(input);
            return prop;

        } catch (IOException ex) {
            throw  new IllegalPathStateException("Sorry, unable to find "+name+".properties");
        }
    }

}
