package hyh.service;

import hyh.dao.UserDao;
import hyh.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserDao dao;

    public int deleteById(long id){
        log.info("删除用户");
        return dao.deleteById(id);
    }

    public int add(User record){
        log.info("增加用户");
        return dao.add(record);
    }

    public List<User> getAll(int index, int count){
        log.info("获取指定数量的用户");
        return dao.getAll(index, count);
    }

    public User getById(long id){
        log.info("通过id查找用户");
        return dao.getById(id);
    }

    public User login(String email, String password){
        log.info("通过邮箱密码获取用户");
        return dao.login(email, password);
    }

    public int editPwd(User record){
        log.info("修改密码");
        return dao.editPwd(record);
    }

    public int update(User record){
        log.info("更新用户");
        return dao.update(record);
    }

    public boolean isExist(String email, String name){
        log.info("判断邮箱是否存在");
        Integer cot = dao.isExist(email, name);
        return cot != null && cot > 0;
    }

    public int getCount(){
        log.info("获得用户数量");
        Integer tmp = dao.getCount();

        return tmp == null ? 0 : tmp;
    }
}
