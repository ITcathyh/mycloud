package hyh.service;

import hyh.dao.UserFileDao;
import hyh.entity.UserFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFileService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserFileDao dao;

    public int deleteById(long id) {
        log.info("删除文件");
        return dao.deleteById(id);
    }

    public int deleteByUserid(long id){
        log.info("通过用户id删除");
        return dao.deleteByUserid(id);
    }

    public int add(UserFile record) {
        log.info("增加文件");
        return dao.add(record);
    }

    public UserFile getById(long id) {
        log.info("获取文件");
        return dao.getById(id);
    }

    public List<UserFile> getByUserid(long id){
        log.info("通过用户id获取文件");
        return dao.getByUserid(id);
    }

    public List<UserFile> getAll(){
        log.info("获取所有文件");
        return dao.getAll();
    }

    public int update(UserFile record) {
        log.info("更新文件");
        return dao.update(record);
    }
}
