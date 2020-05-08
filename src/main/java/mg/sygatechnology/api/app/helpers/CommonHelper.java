package mg.sygatechnology.api.app.helpers;

import mg.sygatechnology.api.AppStarter;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class CommonHelper {

    public static String getPackageName() {
        Package pack = AppStarter.class.getPackage();
        return pack.getName();
    }

    public static Class[] getControllerClasses() {
        try {
            return FileHelper.getClasses(getPackageName()+".app.controllers");
        } catch (ClassNotFoundException | IOException e) {
           throw new IllegalArgumentException(e);
        }
    }

    public static List<String> getMethodParameters(Parameter[] parameters) {
        List<String> parametersList = new ArrayList<>();
        for (Parameter parameter : parameters) {
            parametersList.add(parameter.getName());
        }
        return parametersList;
    }
}
