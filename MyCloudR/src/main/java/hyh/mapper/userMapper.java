package hyh.mapper;

import hyh.mapper.user;

public interface userMapper {
    int deleteByPrimaryKey(Long id);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}