package hyh.service.local;

import com.alibaba.dubbo.config.annotation.Reference;
import hyh.dao.AdvertisementDao;
import hyh.entity.Advertisement;
import hyh.service.RemoteAdvertisementService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AdvertisementService {
    private Logger log = Logger.getLogger(this.getClass());
    @Reference
    private RemoteAdvertisementService dao;

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
        return dao.getByTime(time);
    }

    public Advertisement getById(short id){
        log.info("获取广告");
        return dao.getById(id);
    }
}
