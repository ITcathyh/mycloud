package hyh.dao;

import hyh.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserDao {
    int deleteById(long id);

    int add(User record);

    List<User> getAll(@Param("index") int index, @Param("count") int count);

    User search( String key);

    User getById(long id);

    User login(@Param("email") String email, @Param("password") String password);

    int editPwd(User record);

    int update(User record);

    int updateLoginInfor(@Param("logintime") Timestamp logintime, @Param("ip") String ip);

    Integer isExist(@Param("email") String email, @Param("name") String name);

    Integer getCount();
}