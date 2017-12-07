package nom.learning.springboot.web.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by huangzhangting on 2017/12/5.
 */

@Data
@Component
@PropertySource(value = "classpath:properties/my.properties")
//使用@ConfigurationProperties 或者 @Value 都可以
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
//    @Value("${app.name}")
    private String name;
//    @Value("${app.password}")
    private String password;

}
