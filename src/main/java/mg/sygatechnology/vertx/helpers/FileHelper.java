package mg.sygatechnology.vertx.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static List<String> getFiles(String source) {
        File folder = new File(source);
        File[] files = folder.listFiles();
        List<String> list = new ArrayList();
        for (File file : files)
        {
            if (file.isFile())
            {
                list.add(file.getName());
            }
        }
        return list;
    }

    public static List<String> getFilesWithoutExt(String source) {
        List<String> list = new ArrayList();
        for(String file : getFiles(source)) {
            String[] segment = file.split("\\.");
            int segLength = segment.length-1;
            String newFile = "";
            for(int i = 0; i < segLength; i++) {
                newFile += segment[i]+".";
            }
            list.add( newFile.substring(0, newFile.length() - 1) );
        }
        return list;
    }



}
