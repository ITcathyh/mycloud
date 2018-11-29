package hyh.dao;

import hyh.entity.UserRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRecordDao {
    int add(UserRecord record);

    List<UserRecord> getByUserid(long userid);

    Long getId(@Param("userid") long userid, @Param("tag") String tag, @Param("type") short type);

    int update(@Param("id") long id, @Param("lasttime") Timestamp lasttime);
}