package hyh.service.local;

import com.alibaba.dubbo.config.annotation.Reference;
import hyh.dao.InvitationcodeDao;
import hyh.entity.Invitationcode;
import hyh.service.RemoteInvitationcodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationcodeService {
    private Logger log = Logger.getLogger(this.getClass());
    @Reference
    private RemoteInvitationcodeService dao;

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

    public Invitationcode getByCode(String invitationcode){
        log.info("获取邀请码id、可激活次数");
        return dao.getByCode(invitationcode);
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
