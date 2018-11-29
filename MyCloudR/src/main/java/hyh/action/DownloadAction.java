package hyh.action;

import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.Info.Variable;
import hyh.service.local.UserFileService;
import hyh.service.local.UserService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
/**
 * 用户积分计算
 * @author ITcathyh
 * @version 1.0
 */
@Component
public class DownloadAction {
    private Logger log = Logger.getLogger(this.getClass());

    @Async
    public void updateDownloadInfo(UserFile userfile, UserFileService userfileservice,
                                   UserService userservice, Object obj) {
        DailyInfoAction.setNewdownloads(DailyInfoAction.getNewdownloads() + 1);

        if (obj == null || ((User) obj).getId() != userfile.getUserid()) {
            userfile.setDownloads(userfile.getDownloads() + 1);

            if (!addUserPoint(userfile, userservice.getById(userfile.getUserid()), userservice) ||
                    userfileservice.addDownloads(userfile) != 1) {
                log.error("update download info error");
                Variable.errornum++;
            }
        }
    }

    final private int getPoint(UserFile userfile) {
        return (userfile.getSize() / 1000000 >> 4) + userfile.getSize() % 17;
    }

    final private boolean addUserPoint(UserFile userfile, User user, UserService userservice) {
        if (user == null) {
            return false;
        }

        user.setPoints(user.getPoints() + getPoint(userfile));

        return userservice.update(user) == 1;
    }
}
