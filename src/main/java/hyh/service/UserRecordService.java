package hyh.service;

import hyh.dao.UserRecordDao;
import hyh.entity.UserRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserRecordService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserRecordDao dao;

    public int add(UserRecord record) {
        log.info("增加用户搜索下载记录");
        return dao.add(record);
    }

    public long getId(long userid, String tag, short type) {
        log.info("获取记录ID");
        Long tmp = dao.getId(userid, tag, type);
        return tmp == null ? 0 : tmp;
    }

    public int update(long id, Timestamp lasttime) {
        log.info("更新记录");
        return dao.update(id, lasttime);
    }
}
