package hyh.service;

import hyh.dao.UserRecordDao;
import hyh.entity.UserRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRecordService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserRecordDao dao;

    public int add(UserRecord record){
        log.info("增加用户搜索下载记录");
        return dao.add(record);
    }
}
