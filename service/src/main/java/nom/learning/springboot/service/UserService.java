package nom.learning.springboot.service;

import nom.learning.springboot.dao.mapper.UserExtendDOMapper;
import nom.learning.springboot.dao.model.UserExtendDO;
import nom.learning.springboot.service.bo.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangzhangting on 2017/12/5.
 */
@Service
public class UserService {
    @Resource
    UserExtendDOMapper userExtendDOMapper;


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

}
