package hyh.service;

/**
 * Created by 黄宇航 on 2017/9/20.
 */

import hyh.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;

import static org.testng.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-mvc.xml", "classpath*:/spring-mybatis.xml"})
public class ServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private UserService userservice;
    private Logger log = Logger.getLogger(this.getClass());


    @Test
    public void test() {
        User user = new User();

        user.setQq("123");
        user.setName("1244");
        user.setEmail("1");
        user.setInvitationid((long) 1);
        user.setPassword("1");

        assertTrue(userservice.add(user) == 1);
        assertNull(userservice.getById(124));
        assertTrue(userservice.isExist("1"));
    }

}