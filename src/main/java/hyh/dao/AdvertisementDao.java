package hyh.dao;

import hyh.entity.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdvertisementDao {
    int add(Advertisement record);

    int deleteById(short id);

    List<Advertisement> getAll();

    Advertisement getById(short id);
}