package hyh.dao;

import hyh.entity.UserRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRecordDao {
    int add(UserRecord record);

    List<UserRecord> getByUserid(long userid);
}