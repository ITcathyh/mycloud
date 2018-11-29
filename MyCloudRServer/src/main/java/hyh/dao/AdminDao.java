package hyh.dao;

import hyh.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface AdminDao {
    int add(Admin record);

    Admin getById(long id);

    Admin login(@Param("username") String username, @Param("password") String password);

    int update(Admin record);

    int updateLoginInfor(Admin record);
}