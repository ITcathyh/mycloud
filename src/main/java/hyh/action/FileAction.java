package hyh.action;

import hyh.global.Variable;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public final class FileAction {
    private static final Logger log = Logger.getLogger("hyh.Action.FileAction");
    private static final String FILE_PATH = "file/";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static boolean uploadFile(String path, CommonsMultipartFile uploadfile) {
        if (!makeFolder(path)) {
            return false;
        }

        File file = new File(path + uploadfile.getName());

        try {
            uploadfile.transferTo(file);
        } catch (Exception e) {
            Variable.errornum++;
            log.error(e);
            return false;
        }


        return true;
    }

    public static boolean makeFolder(String path) {
        File file = new File(path);

        if (!file.exists() && !file.mkdirs()) {
            Variable.errornum++;
            log.error("Make floder error");
            return false;
        }

        return true;
    }

    public static boolean transferFile(String path, CommonsMultipartFile uploadfile) {
        try {
            uploadfile.transferTo(new File(path));
        } catch (Exception e) {
            Variable.errornum++;
            log.error(e);
            return false;
        }

        return true;
    }

    public static int getType(String type) {
        if (type.equals("Books")){
            return 1;
        } else if (type.equals("Other")){
            return 3;
        }

        return 1;
    }
}
