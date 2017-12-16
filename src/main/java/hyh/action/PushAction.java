package hyh.action;

import hyh.entity.Advertise;

import java.util.ArrayList;
import java.util.List;

public class PushAction {
    private static final List<Advertise> advertises = new ArrayList<>();
    private static String notice = "Welcome";

    public static List<Advertise> getAdvertise() {
        return advertises;
    }

    public static String getNotice(){
        return notice;
    }


}
