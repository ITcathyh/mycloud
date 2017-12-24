package hyh.service;

/**
 * Created by 黄宇航 on 2017/9/20.
 */

import hyh.action.FileAction;
import hyh.entity.Admin;
import hyh.entity.User;
import hyh.entity.UserFile;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;

import static org.testng.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-mvc.xml", "classpath*:/spring-mybatis.xml"})
public class ServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserRecordService service;
    @Autowired
    private AdvertisementService advertisementservice;

    @Test
    public void test() {
        System.out.println(advertisementservice.getByTime(new Timestamp(System.currentTimeMillis())));
    }

}
