package hyh.action;

import hyh.entity.DailyInfo;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.global.Variable;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class DownloadAction {
    private Logger log = Logger.getLogger(this.getClass());

    @Async
    public void updateDownloadInfo(UserFile userfile, UserFileService userfileservice,
                                   UserService userservice, HttpSession session) {
        synchronized (this){
            DailyInfoAction.setNewdownloads(DailyInfoAction.getNewdownloads() + 1);
        }

        Object obj = session.getAttribute("user");

        if (obj == null || ((User)obj).getId() != userfile.getUserid()){
            obj = null;
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

    final private boolean addUserPoint(UserFile userfile, User user, UserService userservice){
        if (user == null){
            return false;
        }

        user.setPoints(user.getPoints() + getPoint(userfile));

        return userservice.update(user) == 1;
    }
}
