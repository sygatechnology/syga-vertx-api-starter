package mg.sygatechnology.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import mg.sygatechnology.vertx.vertx.helpers.DateHelper;

public class VerticleLaucher {

    public static void deploy(final String environment, final int port){
        final VertxOptions vertxOptions = setVertxOptions();
        final Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.deployVerticle(App.class.getName(), setDepOptions(vertxOptions, environment, port), res -> {
            if(res.succeeded()){


                // Shared Configs
                final LocalMap<String, String> sharedData = vertx.sharedData().getLocalMap("cofing");
                sharedData.put("environment", environment);


                System.out.println("Environment : " + environment);
                System.out.println("Logs App is running at localhost:" + port+". Deployment id : " + res.result());
            } else {
                System.out.println(res.cause());
                System.err.println("Deployment failed!");
            }
            System.err.println("");
            System.err.println("Deployment date time : " + DateHelper.date() + " " + DateHelper.time());
            System.err.println("");
        });
    }

    private static VertxOptions setVertxOptions(){
        final VertxOptions vertxOptions = new VertxOptions();
        final Long executeTime = vertxOptions.getMaxWorkerExecuteTime() * 10;
        vertxOptions.setMaxWorkerExecuteTime(executeTime)
                .setMaxEventLoopExecuteTime(vertxOptions.getMaxEventLoopExecuteTime() * 10)
                .setBlockedThreadCheckInterval(8000);
        return vertxOptions;
    }

    private static DeploymentOptions setDepOptions(final VertxOptions vertxOptions, final String environment, final int port){
        final DeploymentOptions options = new DeploymentOptions()
                .setInstances(Runtime.getRuntime().availableProcessors())
                .setWorker(true)
                .setMaxWorkerExecuteTime(vertxOptions.getMaxWorkerExecuteTime())
                .setConfig(new JsonObject().put("VXENV", environment).put("VXPORT", port));
        return options;
    }

}
