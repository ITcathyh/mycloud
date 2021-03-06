package hyh.service.impl;

import hyh.dao.AdvertisementDao;
import hyh.entity.Advertisement;
import hyh.service.RemoteAdvertisementService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class RemoteAdvertisementServiceImpl implements RemoteAdvertisementService {
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

    public List<Advertisement> getByTime(Timestamp time){
        log.info("获取有效广告");
        return dao.getByTime(time.getTime());
    }

    public Advertisement getById(short id){
        log.info("获取广告");
        return dao.getById(id);
    }
}
