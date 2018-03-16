package hyh.action;

import hyh.entity.Advertisement;
import hyh.entity.PushContent;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.UserFileService;
import hyh.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PushAction {
    private static final List<Advertisement> advertises = new ArrayList<>();
    private static PushContent notice = new PushContent("Hello, I am the push content", "/");
    private static final int HOT_SIZE = 10;

    public static List<Advertisement> getAdvertise() {
        return advertises;
    }

    public static PushContent getNotice() {
        return notice;
    }

    public static void setNotice(PushContent tmp) {
        notice = tmp;
    }

    public static PushContent getRecommend(User user, UserService userservice) {
        return null;
    }

    public static List<PushContent> getHots(UserFileService userfileservice) {
        List<UserFile> files  = userfileservice.getHot(HOT_SIZE);
        List<PushContent> contents = new ArrayList<>(HOT_SIZE);
        String filename, type;

        for (UserFile file:files){
            filename = file.getFilename();
            type = FileAction.fileTypeToString(file.getType());

            contents.add(new PushContent(filename, "search?text=" + filename + "&type=" + type));
        }

        return contents;
    }

}
