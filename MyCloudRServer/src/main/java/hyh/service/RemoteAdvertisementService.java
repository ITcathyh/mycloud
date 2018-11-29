package hyh.service;

import hyh.entity.Advertisement;

import java.sql.Timestamp;
import java.util.List;

public interface RemoteAdvertisementService {
    public int add(Advertisement record);
    public int deleteById(short id);
    public List<Advertisement> getAll();
    public List<Advertisement> getByTime(Timestamp time);
    public Advertisement getById(short id);
}
