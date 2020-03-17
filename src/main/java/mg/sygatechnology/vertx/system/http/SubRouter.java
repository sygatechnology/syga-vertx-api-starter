package mg.sygatechnology.vertx.system.http;

import mg.sygatechnology.vertx.system.interfaces.SubRouterInt;

public class SubRouter implements SubRouterInt {
    protected Router router;

    public SubRouter(Router r) {
        this.router = r;
    }

    @Override
    public SubRouter registerGetHttpMethod(String path) {
        this.router.registerGetHttpMethod(path);
        return this;
    }

    @Override
    public SubRouter registerPostHttpMethod(String path) {
        this.router.registerPostHttpMethod(path);
        return this;
    }

    @Override
    public SubRouter registerPutHttpMethod(String path) {
        this.router.registerPutHttpMethod(path);
        return this;
    }

    @Override
    public SubRouter registerDeleteHttpMethod(String path) {
        this.router.registerDeleteHttpMethod(path);
        return this;
    }
}
