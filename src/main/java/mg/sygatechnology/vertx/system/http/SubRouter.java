package mg.sygatechnology.vertx.system.http;

import mg.sygatechnology.vertx.system.interfaces.SubRouterInt;

public class SubRouter implements SubRouterInt {
    protected Router router;

    public SubRouter(Router r) {
        this.router = r;
    }

    @Override
    public void subGetHttpMethod(String path) {
        this.router.registerGetHttpMethod(path);
    }

    @Override
    public void subPostHttpMethod(String path) {
        this.router.registerPostHttpMethod(path);
    }

    @Override
    public void subPutHttpMethod(String path) {
        this.router.registerPutHttpMethod(path);
    }

    @Override
    public void subDeleteHttpMethod(String path) {
        this.router.registerDeleteHttpMethod(path);
    }
}
