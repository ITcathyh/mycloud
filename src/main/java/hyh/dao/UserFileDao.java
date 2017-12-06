package hyh.dao;

import hyh.entity.UserFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileDao {
    int deleteById(long id);

    int deleteByUserid(long id);

    int add(UserFile record);

    UserFile getById(long id);

    List<UserFile> getByUserid(long id);

    List<UserFile> getAll();

    List<UserFile> search(@Param("index") int index, @Param("count") int count, @Param("key") String key);

    int update(UserFile record);
}