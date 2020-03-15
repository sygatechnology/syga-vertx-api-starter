package mg.sygatechnology.vertx.system.interfaces;

public interface RouterInt {

    void registerGetHttpMethod(String path);

    void registerPostHttpMethod(String path);

    void registerPutHttpMethod(String path);

    void registerDeleteHttpMethod(String path);

}
