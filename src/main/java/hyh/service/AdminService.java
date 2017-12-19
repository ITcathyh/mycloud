package hyh.service;

import hyh.dao.AdminDao;
import hyh.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AdminService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private AdminDao dao;

    public int add(Admin record) {
        log.info("增加管理员");
        return dao.add(record);
    }

    public Admin getById(long id) {
        log.info("获取管理员");
        return dao.getById(id);
    }

    public Admin login(String username, String password) {
        log.info("管理员登录");
        return dao.login(username, password);
    }

    public int update(Admin record) {
        log.info("更新管理员信息");
        return dao.update(record);
    }

    public int updateLoginInfor(Admin record) {
        log.info("更新管理员登录信息");
        return dao.updateLoginInfor(record);
    }
}
