package hyh.service.impl;

import hyh.dao.AdminDao;
import hyh.entity.Admin;
import hyh.service.RemoteAdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class RemoteAdminServiceImpl implements RemoteAdminService {
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
