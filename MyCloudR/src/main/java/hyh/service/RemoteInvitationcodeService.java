package hyh.service;

import hyh.entity.Invitationcode;

import java.util.List;

public interface RemoteInvitationcodeService {
    public int deleteById(long id);
    public int add(Invitationcode record);
    public Invitationcode getById(long id);
    public Invitationcode getByCode(String invitationcode) ;
    public List<Invitationcode> getAll() ;
    public int update(Invitationcode record);
    public Integer isExist(String invitationcode);
}