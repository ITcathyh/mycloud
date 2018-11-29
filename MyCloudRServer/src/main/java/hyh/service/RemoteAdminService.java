package hyh.service;

import hyh.entity.Admin;

public interface RemoteAdminService {
    public int add(Admin record);
    public Admin getById(long id) ;
    public Admin login(String username, String password) ;
    public int update(Admin record) ;
    public int updateLoginInfor(Admin record);
}
