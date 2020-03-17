package mg.sygatechnology.vertx.system.interfaces;

public interface SubRouterInt {

    SubRouterInt registerGetHttpMethod(String path);

    SubRouterInt registerPostHttpMethod(String path);

    SubRouterInt registerPutHttpMethod(String path);

    SubRouterInt registerDeleteHttpMethod(String path);

}
