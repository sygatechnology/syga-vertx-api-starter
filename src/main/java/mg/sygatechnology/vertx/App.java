package mg.sygatechnology.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import mg.sygatechnology.vertx.system.Common;
import mg.sygatechnology.vertx.configs.ConfigItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App extends AbstractVerticle {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        String environment;
        if(args.length > 0){
            environment = args[0];
        } else {
            environment = "development";
        }

        ConfigItem confItem = Common.registerConfig(environment, "environments");

        VerticleLaucher.deploy(environment, confItem.getInteger("port"));

    }

    @Override
    public void start(Promise<Void> future) {

        Common.initApp(vertx);

        final String environment = config().getString("VXENV");
        final int port = config().getInteger("VXPORT");
        vertx.createHttpServer().requestHandler(Common.getRouter()).listen(port, result -> {
            if(result.succeeded()){
                System.out.println("Server start at localhost:" + port);
                future.complete();
            } else {
                future.fail(result.cause());
            }
        });
    }

}
