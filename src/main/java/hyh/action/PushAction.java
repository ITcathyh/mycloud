package hyh.action;

import hyh.entity.Advertise;
import hyh.entity.PushContent;
import hyh.entity.User;
import hyh.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class PushAction {
    private static final List<Advertise> advertises = new ArrayList<>();
    private static PushContent notice = null;

    public static List<Advertise> getAdvertise() {
        return advertises;
    }

    public static PushContent getNotice(){
        return notice;
    }

    public static void setNotice(PushContent snotice){
        notice = snotice;
    }

    public static PushContent getRecommend(User user, UserService userservice){
        return null;
    }

    public static List<PushContent> getHot(int size){
        return null;
    }

}
