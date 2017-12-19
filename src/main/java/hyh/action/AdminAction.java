package hyh.action;

import hyh.entity.DailyInfo;

public class AdminAction {
    private static final DailyInfo dailyifo = new DailyInfo();

    public static DailyInfo getDailyifo() {
        return dailyifo;
    }
}
