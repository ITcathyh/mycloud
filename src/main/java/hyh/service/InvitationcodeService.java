package hyh.service;

import hyh.dao.InvitationcodeDao;
import hyh.entity.Invitationcode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationcodeService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private InvitationcodeDao dao;

    public int deleteById(long id){
        log.info("删除邀请码");
        return dao.deleteById(id);
    }

    public int add(Invitationcode record){
        log.info("增加邀请码");
        return dao.add(record);
    }

    public Invitationcode getById(long id){
        log.info("获取邀请码");
        return dao.getById(id);
    }

    public List<Invitationcode> getAll(){
        log.info("获取所有邀请码");
        return dao.getAll();
    }

    public int update(Invitationcode record){
        log.info("更新邀请码");
        return dao.update(record);
    }

    public Integer isExist(String invitationcode){
        log.info("判断邀请码是否存在");
        return dao.isExist(invitationcode);
    }
}
