package hyh.service.impl;

import hyh.dao.UserFileDao;
import hyh.entity.UserFile;
import hyh.service.RemoteUserFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class RemoteUserFileServiceImpl implements RemoteUserFileService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserFileDao dao;

    public int deleteById(long id) {
        log.info("删除文件");
        return dao.deleteById(id);
    }

    public int deleteByUserid(long id) {
        log.info("通过用户id删除");
        return dao.deleteByUserid(id);
    }

    public int add(UserFile record) {
        log.info("增加文件");
        return dao.add(record);
    }

    public int getCountByUserid(long userid) {
        log.info("获取用户文件数量");
        Integer tmp = dao.getCountByUserid(userid);
        return tmp == null ? 0 : tmp;
    }

    public UserFile getById(long id) {
        log.info("获取文件");
        return dao.getById(id);
    }

    public List<UserFile> getByUserid(long userid, int index, int count) {
        log.info("通过用户ID获取文件");
        return dao.getByUserid(userid, index, count);
    }

    public List<UserFile> getByType(short type, int index, int count) {
        log.info("通过类型获取文件");
        return dao.getByType(type, index, count);
    }

    public List<UserFile> getAll(int index, int count) {
        log.info("获取所有文件");
        return dao.getAll(index, count);
    }

    public List<UserFile> search(int index, int count, String key) {
        log.info("查找文件");
        return dao.search(index, count, key);
    }

    public List<UserFile> searchByUseridOrFiledetail(int index, int count, String key, long userid) {
        log.info("通过ID和文件名查找文件");
        return dao.searchByUseridOrFiledetail(index, count, key, userid);
    }

    public List<UserFile> searchByType(short type, int index, int count, String key) {
        log.info("通过类型和关键字查找文件");
        return dao.searchByType(type, index, count, key);
    }

    public List<UserFile> searchByUserid(long userid, int index, int count, String key) {
        log.info("通过用户和关键字查找文件");
        return dao.searchByUserid(userid, index, count, key);
    }

    public List<UserFile> getHot(int hotsize){
        log.info("获取热门资源");
        return dao.getHot(hotsize);
    }

    public int update(UserFile record) {
        log.info("更新文件");
        return dao.update(record);
    }

    public int addDownloads(UserFile record) {
        log.info("更新下载量");
        return dao.addDownloads(record);
    }

    public int getCount() {
        log.info("获得资源数量");
        Integer tmp = dao.getCount();

        return tmp == null ? 0 : tmp;
    }

    public int getDownloads() {
        log.info("获得总下载量");
        Integer tmp = dao.getDownloads();

        return tmp == null ? 0 : tmp;
    }
}
