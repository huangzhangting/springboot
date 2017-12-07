package nom.learning.springboot.test;

import nom.learning.springboot.dao.model.UserExtendDO;
import nom.learning.springboot.dao.slave.mapper.SlaveUserExtendDOMapper;
import nom.learning.springboot.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by huangzhangting on 2017/12/6.
 */
public class UserServiceTest extends BaseTest {
    @Resource
    UserService userService;
    @Resource
    SlaveUserExtendDOMapper slaveUserExtendDOMapper;


    @Test
    public void test_add(){
        UserExtendDO userExtendDO = new UserExtendDO();
        userExtendDO.setMobile("15158036123");
        userExtendDO.setUserId(3);

        userService.addUser(userExtendDO);

        addToSlave(userExtendDO);
    }
    private void addToSlave(UserExtendDO user){
        Date now = new Date();
        user.setGmtCreate(now);
        user.setGmtUpdate(now);
        user.setRegisterTime(now);

        slaveUserExtendDOMapper.insertSelective(user);
    }

    @Test
    public void test_query(){
        Integer id = 1;
        System.out.println(userService.getUserById(id));
        System.out.println(slaveUserExtendDOMapper.selectByPrimaryKey(id));
    }

}
