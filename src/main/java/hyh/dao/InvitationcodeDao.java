package hyh.dao;

import hyh.entity.Invitationcode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationcodeDao {
    int deleteById(long id);

    int add(Invitationcode record);

    Invitationcode getById(long id);

    Invitationcode getByCode(String invitationcode);

    List<Invitationcode> getAll();

    int update(Invitationcode record);

    Integer isExist(String invitationcode);
}