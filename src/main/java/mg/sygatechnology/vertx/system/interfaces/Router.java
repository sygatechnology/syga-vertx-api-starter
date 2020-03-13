package mg.sygatechnology.vertx.system.interfaces;

public interface Router {

    public void registerGet(String path);

    public void registerPost(String path);

    public void registerPut(String path);

    public void registerDelete(String path);

    public void registerResource(String path);

}
