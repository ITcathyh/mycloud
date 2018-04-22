package hyh.action;

import hyh.Info.Variable;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
/**
 * 资源在服务端的操作
 * @author ITcathyh
 * @version 1.0
 */
public final class FileAction {
    private static final Logger log = Logger.getLogger("hyh.Action.FileAction");
    private static final String FILE_PATH = "file/";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static boolean transferFile(String path, CommonsMultipartFile uploadfile) {
        if (!makeFolder(path)) {
            return false;
        }

        try {
            uploadfile.transferTo(new File(path + uploadfile.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            Variable.errornum++;
            log.error(e);
            return false;
        }

        return true;
    }

    public static short getType(String type) {
        switch (type) {
            case "Books":
                return 1;
            case "Courseware":
                return 2;
            case "Pictures":
                return 3;
            case "Other":
                return 4;
        }

        return -1;
    }

    public static String fileTypeToString(short type){
        if (type == 1){
            return "Books";
        }else if (type == 2){
            return "Courseware";
        }else  if(type == 3){
            return "Pictures";
        } else if (type == 4) {
            return "Other";
        }

        return null;
    }

    private static boolean makeFolder(String path) {
        File file = new File(path);

        if (!file.exists() && !file.mkdirs()) {
            Variable.errornum++;
            log.error("Make floder error");
            return false;
        }

        return true;
    }

}
