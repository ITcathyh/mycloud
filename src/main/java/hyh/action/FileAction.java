package hyh.action;

import hyh.global.Variable;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public final class FileAction {
    private static final Logger log = Logger.getLogger("hyh.Action.FileAction");
    private static final String FILE_PATH = "file/";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static boolean uploadFile(String path, CommonsMultipartFile uploadfile) {
        if (!makeFolder(path)){
            return false;
        }

        File file = new File(path + uploadfile.getName());

        try {
            uploadfile.transferTo(file);
        }catch (Exception e){
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
}
