package hyh.dao;

import hyh.entity.Advertisement;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface AdvertisementDao {
    int add(Advertisement record);

    int deleteById(short id);

    List<Advertisement> getAll();

    List<Advertisement> getByTime(long time);

    Advertisement getById(short id);
}