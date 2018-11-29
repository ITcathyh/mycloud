package hyh.service;

import hyh.entity.UserFile;

import java.util.List;

public interface RemoteUserFileService {
    public int deleteById(long id) ;
    public int deleteByUserid(long id) ;
    public int add(UserFile record) ;
    public int getCountByUserid(long userid);
    public UserFile getById(long id);
    public List<UserFile> getByUserid(long userid, int index, int count) ;
    public List<UserFile> getByType(short type, int index, int count);
    public List<UserFile> getAll(int index, int count) ;
    public List<UserFile> search(int index, int count, String key) ;
    public List<UserFile> searchByUseridOrFiledetail(int index, int count, String key, long userid);
    public List<UserFile> searchByType(short type, int index, int count, String key) ;
    public List<UserFile> searchByUserid(long userid, int index, int count, String key) ;
    public List<UserFile> getHot(int hotsize);
    public int update(UserFile record) ;
    public int addDownloads(UserFile record);
    public int getCount() ;
    public int getDownloads() ;
}
