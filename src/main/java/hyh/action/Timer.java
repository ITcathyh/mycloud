package hyh.action;

import hyh.entity.DailyInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {
    @Scheduled(cron = "50 59 23 * * ?")
    public void clearDailyInfo() {
        DailyInfoAction.setActive(0);
        DailyInfoAction.setNewdownloads(0);
        DailyInfoAction.setNewfiles(0);
        DailyInfoAction.setNewusers(0);
    }
}
