package hyh.action;

import hyh.entity.UserRecord;
import hyh.service.local.UserRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
/**
 * 记录用户行为
 * @author ITcathyh
 * @version 1.0
 */
@Component
public class RecordAction {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserRecordService service;

    @Async
    public void addRecord(Object userid, String tag, short type) {
        if (type == -1){
            return;
        }

        if (tag == null || tag.length() == 0){
            tag = "-1";
        }

        if (userid == null) {
            addRecord(0, tag, type);
        } else {
            addRecord((long) userid, tag, type);
        }
    }

    private void addRecord(long userid, String tag, short type) {
        addUserRecord(userid, tag, type);
    }

    private void addUserRecord(long userid, String tag, short type) {
        try {
            long id = service.getId(userid, tag, type);

            if (id != 0) {
                service.update(id, new Timestamp(System.currentTimeMillis()));
            } else {
                UserRecord userrecord = new UserRecord();

                userrecord.setTag(tag);
                userrecord.setType(type);
                userrecord.setUserid(userid);

                service.add(userrecord);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }
}
