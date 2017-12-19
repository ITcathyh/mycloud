package hyh.action;

import hyh.entity.DailyInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {
    @Scheduled(cron = "50 59 23 * * ?")
    public void clearDailyInfo() {
        DailyInfo dailyinfo = AdminAction.getDailyifo();

        dailyinfo.setActive(0);
        dailyinfo.setNewdownloads(0);
        dailyinfo.setNewfiles(0);
        dailyinfo.setNewusers(0);
    }
}
