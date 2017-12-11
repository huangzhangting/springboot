package nom.learning.springboot.test;

import nom.learning.springboot.dao.model.UserExtendDO;
import nom.learning.springboot.service.utils.RedisUtil;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangzhangting on 2017/12/7.
 */
public class RedisTest extends BaseTest {
    private static final String keyPre = "springboot:test:";

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Test
    public void test(){
        String key = keyPre + "key";
        String value = "test";

        stringRedisTemplate.opsForValue().set(key, value, 600, TimeUnit.SECONDS);

    }

    @Test
    public void test_2(){
        Set<String> keys = stringRedisTemplate.keys(keyPre + "*");
        System.out.println(keys);
    }

    @Test
    public void test_get(){
        String key = keyPre + "key";
        String value = stringRedisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

    @Test
    public void test_json(){
        UserExtendDO user = new UserExtendDO();
        user.setUserId(1);
        user.setMobile("123123");

        String key = keyPre + "user";
        RedisUtil.setJson(key, user, 600);
    }

    @Test
    public void test_getjson(){
        String key = keyPre + "user";
        UserExtendDO user = RedisUtil.getFromJson(key, UserExtendDO.class);
        System.out.println(user);
    }

}
