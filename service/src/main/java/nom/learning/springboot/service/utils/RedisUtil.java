package nom.learning.springboot.service.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangzhangting on 2017/12/7.
 */
@Slf4j
@Component
public class RedisUtil {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 以json形式存储
     *
     * @param key
     * @param object
     * @param timeout 单位：秒
     */
    public void setJson(String key, Object object, long timeout){
        String value = JSON.toJSONString(object);
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @param cla
     * @param <D>
     * @return
     */
    public <D> D getFromJson(String key, Class<D> cla){
        String value = stringRedisTemplate.opsForValue().get(key);
        if(value == null){
            return null;
        }
        return JSON.parseObject(value, cla);
    }

}
