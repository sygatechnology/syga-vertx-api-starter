package mg.sygatechnology.vertx.system.interfaces;

public interface SubRouterInt {

    void subGetHttpMethod(String path);

    void subPostHttpMethod(String path);

    void subPutHttpMethod(String path);

    void subDeleteHttpMethod(String path);

}
