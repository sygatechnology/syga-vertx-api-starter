package mg.sygatechnology.api.system.interfaces;

public interface RouterInt {

    void registerGetHttpMethod(String path, String controllerMethodName, String produces);

    void registerPostHttpMethod(String path, String controllerMethodName, String produces, String consumes);

    void registerPutHttpMethod(String path, String controllerMethodName, String produces, String consumes);

    void registerDeleteHttpMethod(String path, String controllerMethodName, String produces, String consumes);

}
