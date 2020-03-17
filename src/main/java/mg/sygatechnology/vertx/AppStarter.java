package mg.sygatechnology.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import mg.sygatechnology.vertx.configs.Config;
import mg.sygatechnology.vertx.configs.ConfigItem;
import mg.sygatechnology.vertx.system.http.HttpVerticle;

public class AppStarter {

    public static void main(String[] args) {

        String environment;
        if(args.length > 0){
            environment = args[0];
        } else {
            environment = "development";
        }

        final Vertx vertx = Vertx.vertx();
        final VertxOptions vertxOptions = setVertxOptions();
        vertx.deployVerticle(HttpVerticle.class.getName(), setDepOptions(vertxOptions, environment));

    }

    private static VertxOptions setVertxOptions(){
        final VertxOptions vertxOptions = new VertxOptions();
        final Long executeTime = vertxOptions.getMaxWorkerExecuteTime() * 10;
        vertxOptions.setMaxWorkerExecuteTime(executeTime)
                .setMaxEventLoopExecuteTime(vertxOptions.getMaxEventLoopExecuteTime() * 10)
                .setBlockedThreadCheckInterval(8000);
        return vertxOptions;
    }

    private static DeploymentOptions setDepOptions(final VertxOptions vertxOptions, String environment){
        ConfigItem configItem = Config.get(environment, true, "environments");
        int instances = 1;
        if(configItem.keyExists("instances")) {
            instances = configItem.getInteger("instances");
        }
        if(configItem.keyExists("instances.available.processors") && configItem.getBoolean("available.processors")) {
            instances = Runtime.getRuntime().availableProcessors();
        }
        final DeploymentOptions options = new DeploymentOptions()
                .setInstances(instances)
                .setWorker(true)
                .setMaxWorkerExecuteTime(vertxOptions.getMaxWorkerExecuteTime())
                .setConfig(new JsonObject().put("VXENV", environment));
        return options;
    }

}
