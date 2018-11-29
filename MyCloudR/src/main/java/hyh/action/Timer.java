package hyh.action;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 重置每日记录
 * @author ITcathyh
 * @version 1.0
 */
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
