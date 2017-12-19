package hyh.action;

import hyh.entity.DailyInfo;

public final class DailyInfoAction {
    private static final DailyInfo dailyifo = new DailyInfo();

    public static DailyInfo getDailyifo() {
        return dailyifo;
    }

    public static int getNewusers() {
        return dailyifo.getNewusers();
    }

    public static void setNewusers(int newusers) {
        dailyifo.setNewusers(newusers);
    }

    public static int getActive() {
        return dailyifo.getActive();
    }

    public static void setActive(int active) {
        dailyifo.setActive(active);
    }

    public static int getNewfiles() {
        return dailyifo.getNewfiles();
    }

    public static void setNewfiles(int newfiles) {
        dailyifo.setNewfiles(newfiles);
    }

    public static int getNewdownloads() {
        return dailyifo.getNewdownloads();
    }

    public static void setNewdownloads(int newdownloads) {
        dailyifo.setNewdownloads(newdownloads);
    }
}
