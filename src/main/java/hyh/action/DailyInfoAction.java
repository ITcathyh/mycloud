package hyh.action;

import hyh.entity.DailyInfo;

public final class DailyInfoAction {
    private static final DailyInfo dailyifo = new DailyInfo();

    synchronized public static DailyInfo getDailyifo() {
        return dailyifo;
    }

    synchronized public static int getNewusers() {
        return dailyifo.getNewusers();
    }

    synchronized public static void setNewusers(int newusers) {
        dailyifo.setNewusers(newusers);
    }

    synchronized public static int getActive() {
        return dailyifo.getActive();
    }

    synchronized public static void setActive(int active) {
        dailyifo.setActive(active);
    }

    synchronized public static int getNewfiles() {
        return dailyifo.getNewfiles();
    }

    synchronized public static void setNewfiles(int newfiles) {
        dailyifo.setNewfiles(newfiles);
    }

    synchronized public static int getNewdownloads() {
        return dailyifo.getNewdownloads();
    }

    synchronized public static void setNewdownloads(int newdownloads) {
        dailyifo.setNewdownloads(newdownloads);
    }
}
