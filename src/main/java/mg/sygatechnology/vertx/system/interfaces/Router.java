package mg.sygatechnology.vertx.system.interfaces;

public interface Router {

    public void registerGetHttpMethod(String path);

    public void registerPostHttpMethod(String path);

    public void registerPutHttpMethod(String path);

    public void registerDeleteHttpMethod(String path);

}
