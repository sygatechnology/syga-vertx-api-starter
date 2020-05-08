package mg.sygatechnology.api.system.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mg.sygatechnology.api.configs.App;
import mg.sygatechnology.api.configs.ConfigItem;
import mg.sygatechnology.api.system.Common;

public class HttpVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LogManager.getLogger(HttpVerticle.class);

    @Override
    public void start(Promise<Void> promise) {
        System.getProperty("appenv");
        ConfigItem configItem = Common.config("app");
        int portNumber = configItem.getInteger("port");

        App.initRouter(vertx);
        Common.initControllers();

        vertx.createHttpServer()
                .requestHandler(App.getRouter())
                .listen(configItem.getInteger("port"), ar -> {
                    if (ar.succeeded()) {
                        System.out.println("HTTP server running on port " + portNumber);
                        LOGGER.info("HTTP server running on port " + portNumber);
                        promise.complete();
                    } else {
                        System.out.println(ar.cause().getMessage());
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
