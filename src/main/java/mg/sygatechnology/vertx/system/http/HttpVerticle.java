package mg.sygatechnology.vertx.system.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import mg.sygatechnology.vertx.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mg.sygatechnology.vertx.configs.AppConfig;
import mg.sygatechnology.vertx.configs.ConfigItem;
import mg.sygatechnology.vertx.system.Common;

public class HttpVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LogManager.getLogger(HttpVerticle.class);

    @Override
    public void start(Promise<Void> promise) throws Exception {
        String environment = config().getString("VXENV");

        ConfigItem configItem = Common.config(environment);
        int portNumber = configItem.getInteger("port");

        AppConfig.initRouter(vertx);
        AppConfig.initControllers();

        vertx.createHttpServer()
                .requestHandler(AppConfig.getRouter())
                .listen(configItem.getInteger("port"), ar -> {
                    if (ar.succeeded()) {
                        LOGGER.info("HTTP server running on port " + portNumber);
                        promise.complete();
                    } else {
                        LOGGER.error("Could not start a HTTP server", ar.cause());
                        promise.fail(ar.cause());
                    }
                });
    }

    @Override
    public void stop(Promise<Void> promise) throws Exception {
        LOGGER.info("HTTP server stopped");
    }

}
