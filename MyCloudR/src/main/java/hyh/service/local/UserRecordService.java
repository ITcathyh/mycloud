package hyh.service.local;

import com.alibaba.dubbo.config.annotation.Reference;
import hyh.entity.UserRecord;
import hyh.service.RemoteUserRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserRecordService {
    private Logger log = Logger.getLogger(this.getClass());
    @Reference
    private RemoteUserRecordService dao;

    public int add(UserRecord record) {
        log.info("增加用户搜索下载记录");
        return dao.add(record);
    }

    public long getId(long userid, String tag, short type) {
        log.info("获取记录ID");
        return dao.getId(userid, tag, type);
    }

    public int update(long id, Timestamp lasttime) {
        log.info("更新记录");
        return dao.update(id, lasttime);
    }
}
