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

    Integer getCountByUserid(long userid);

    UserFile getById(long id);

    List<UserFile> getByUserid(@Param("userid") long useridt, @Param("index") int index,
                               @Param("count") int count);

    List<UserFile> getByType(@Param("type") short type, @Param("index") int index,
                             @Param("count") int count);

    List<UserFile> getAll();

    List<UserFile> search(@Param("index") int index, @Param("count") int count,
                          @Param("key") String key);

    List<UserFile> searchByType(@Param("type") short type, @Param("index") int index,
                                @Param("count") int count, @Param("key") String key);

    List<UserFile> searchByUserid(@Param("userid") long userid, @Param("index") int index,
                                  @Param("count") int count, @Param("key") String key);

    int update(UserFile record);

    int addDownloads(UserFile record);

    Integer getCount();

    Integer getDownloads();
}