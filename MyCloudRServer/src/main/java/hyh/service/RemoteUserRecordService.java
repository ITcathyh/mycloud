package hyh.service;

import hyh.entity.UserRecord;

import java.sql.Timestamp;

public interface RemoteUserRecordService {
    public int add(UserRecord record);
    public long getId(long userid, String tag, short type);
    public int update(long id, Timestamp lasttime);
}
