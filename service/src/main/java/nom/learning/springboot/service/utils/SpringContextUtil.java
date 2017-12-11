package nom.learning.springboot.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by huangzhangting on 2017/12/11.
 */
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.applicationContext == null){
            SpringContextUtil.applicationContext = applicationContext;
            log.info("SpringContextUtil init success. applicationContext: {}", applicationContext);
        }
    }

    //获取上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过名字获取上下文中的bean
    public static <T> T getBean(String name, Class<T> cla){
        return applicationContext.getBean(name, cla);
    }

    //通过类型获取上下文中的bean
    public static <T> T getBean(Class<T> cla){
        return applicationContext.getBean(cla);
    }

}
