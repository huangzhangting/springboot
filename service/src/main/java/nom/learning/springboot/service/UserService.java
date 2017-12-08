package nom.learning.springboot.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import nom.learning.springboot.dao.mapper.UserExtendDOMapper;
import nom.learning.springboot.dao.model.UserExtendDO;
import nom.learning.springboot.service.bo.User;
import nom.learning.springboot.service.mq.rocketmq.MQException;
import nom.learning.springboot.service.mq.rocketmq.SimpleProducer;
import nom.learning.springboot.service.mq.rocketmq.StringMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangzhangting on 2017/12/5.
 */
@Slf4j
@Service
public class UserService {
    @Resource
    UserExtendDOMapper userExtendDOMapper;
    @Resource
    SimpleProducer simpleProducer;


    public List<User> userList(){
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("111");
        list.add(user);

        user = new User();
        user.setId(2);
        user.setName("hzt");
        list.add(user);

        return list;
    }

    public UserExtendDO getUserById(Integer id){
        return userExtendDOMapper.selectByPrimaryKey(id);
    }

    public void addUser(UserExtendDO user){
        Assert.notNull(user, "参数不能为空");

        Date now = new Date();
        user.setGmtCreate(now);
        user.setGmtUpdate(now);
        user.setRegisterTime(now);

        userExtendDOMapper.insertSelective(user);
    }

    public void register(){
        UserExtendDO user = new UserExtendDO();
        user.setMobile("test123");
        user.setUserId(123);
        StringMessage message = new StringMessage(JSON.toJSONString(user));
        try {
            log.info("register mq: {}", JSON.toJSONString(message));
            simpleProducer.sendMessage(message);
        } catch (MQException e) {
            log.error("", e);
        }
    }

}
