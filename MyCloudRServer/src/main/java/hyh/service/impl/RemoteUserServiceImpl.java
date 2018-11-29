package hyh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import hyh.dao.UserDao;
import hyh.entity.User;
import hyh.service.RemoteUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class RemoteUserServiceImpl implements RemoteUserService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserDao dao;

    public int deleteById(long id) {
        log.info("删除用户");
        return dao.deleteById(id);
    }

    public int add(User record) {
        log.info("增加用户");
        return dao.add(record);
    }

    public List<User> getAll(int index, int count) {
        log.info("获取指定数量的用户");
        return dao.getAll(index, count);
    }

    public List<User> search(int index, int count, String key) {
        log.info("查找用户");
        return dao.search(index, count, key);
    }

    public User getById(long id) {
        log.info("通过id查找用户");
        return dao.getById(id);
    }

    public User login(String email, String password) {
        log.info("通过邮箱密码获取用户");
        return dao.login(email, password);
    }

    public int editPwd(User record) {
        log.info("修改用户密码");
        return dao.editPwd(record);
    }

    public int editInfo(long id, String qq) {
        log.info("修改用户资料");
        return dao.editInfo(id, qq);
    }

    public int update(User record) {
        log.info("更新用户");
        return dao.update(record);
    }

    public boolean isExist(String email, String name) {
        log.info("判断邮箱是否存在");
        Integer cot = dao.isExist(email, name);
        return cot != null && cot > 0;
    }

    public boolean isExistById(long id) {
        log.info("判断用户是否存在");
        Integer cot = dao.isExistById(id);
        return cot != null && cot > 0;
    }

    public int getCount() {
        log.info("获得用户数量");
        Integer tmp = dao.getCount();

        return tmp == null ? 0 : tmp;
    }
}
