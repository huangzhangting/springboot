package nom.learning.springboot.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by huangzhangting on 2017/12/8.
 */
@Configuration
//导入xml配置文件
@ImportResource(locations = {
        "classpath:spring-beans.xml"
//        , "classpath:mq-config.xml"
})
public class ServiceConfig {
}
