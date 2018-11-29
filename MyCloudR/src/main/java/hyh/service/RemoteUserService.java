package hyh.service;

import hyh.entity.User;

import java.util.List;

public interface RemoteUserService {
    public int deleteById(long id);
    public int add(User record) ;
    public List<User> getAll(int index, int count);
    public List<User> search(int index, int count, String key) ;
    public User getById(long id) ;
    public User login(String email, String password);
    public int editPwd(User record) ;
    public int editInfo(long id, String qq) ;
    public int update(User record) ;
    public boolean isExist(String email, String name) ;
    public boolean isExistById(long id) ;
    public int getCount() ;
}
