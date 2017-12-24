package hyh.service;

import hyh.dao.AdvertisementDao;
import hyh.entity.Advertisement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private AdvertisementDao dao;

    public int add(Advertisement record){
        log.info("增加广告");
        return dao.add(record);
    }

    public int deleteById(short id){
        log.info("删除广告");
        return dao.deleteById(id);
    }

    public List<Advertisement> getAll(){
        log.info("获取所有广告");
        return dao.getAll();
    }

    public Advertisement getById(short id){
        log.info("获取广告");
        return dao.getById(id);
    }
}
