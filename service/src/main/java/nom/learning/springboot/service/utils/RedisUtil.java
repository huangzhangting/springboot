package nom.learning.springboot.service.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangzhangting on 2017/12/7.
 */
@Slf4j
public class RedisUtil {
    private static StringRedisTemplate redisTemplate;
    static {
        redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
    }

    /**
     * 以json形式存储
     *
     * @param key
     * @param object
     * @param timeout 单位：秒
     */
    public static void setJson(String key, Object object, long timeout){
        String value = JSON.toJSONString(object);
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @param cla
     * @param <D>
     * @return
     */
    public static  <D> D getFromJson(String key, Class<D> cla){
        String value = redisTemplate.opsForValue().get(key);
        if(value == null){
            return null;
        }
        return JSON.parseObject(value, cla);
    }

}
