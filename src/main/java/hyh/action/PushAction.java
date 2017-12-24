package hyh.action;

import hyh.entity.Advertisement;
import hyh.entity.PushContent;
import hyh.entity.User;
import hyh.service.UserService;

import java.util.ArrayList;
import java.util.List;

public final class PushAction {
    private static final List<Advertisement> advertises = new ArrayList<>();
    private static PushContent notice = new PushContent("Hello, I am the push content", "/");
    private static int hotsize = 10;

    public static List<Advertisement> getAdvertise() {
        return advertises;
    }

    public static PushContent getNotice(){
        return notice;
    }

    public static void setNotice(PushContent tmp){
        notice = tmp;
    }

    public static void saetHotSize(int tmp){
        hotsize = tmp;
    }

    public static PushContent getRecommend(User user, UserService userservice){
        return null;
    }

    public static List<PushContent> getHot(){
        return null;
    }

}
