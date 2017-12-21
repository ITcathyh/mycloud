package hyh.action;

import hyh.entity.User;
import hyh.entity.UserRecord;
import hyh.resolver.ExcetionResolver;
import hyh.service.UserRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class RecordAction {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserRecordService service;

    @Async
    public void addRecord(Object obj, String tag, short type) {
        if (obj == null) {
            addRecord(tag, type);
        } else {
            addRecord((long) obj, tag, type);
        }
    }

    private void addRecord(long userid, String tag, short type) {
        addUserRecord(userid, tag, type);
    }

    private void addRecord(String tag, short type) {
        addUserRecord(0, tag, type);
    }

    private void addUserRecord(long userid, String tag, short type) {
        UserRecord userrecord = new UserRecord();

        userrecord.setLasttime(new Timestamp(System.currentTimeMillis()));
        userrecord.setTag(tag);
        userrecord.setType(type);
        userrecord.setTime(1);
        userrecord.setUserid(userid);

        try {
            service.add(userrecord);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
