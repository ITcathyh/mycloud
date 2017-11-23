package hyh.dao;

import hyh.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int deleteById(long id);

    int add(User record);

    User getById(long id);

    User getByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    int update(User record);

    Integer isExist(String email);
}