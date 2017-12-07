package nom.learning.springboot.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by huangzhangting on 2017/12/5.
 */
//默认扫描同级包，以及下级包
@SpringBootApplication(
        scanBasePackages={
                "nom.learning.springboot.dao.config"
                , "nom.learning.springboot.service"
                , "nom.learning.springboot.web"
        }
)
//扫描mybatis mapper（单个数据源直接这么配置即可）
//@MapperScan(basePackages={"nom.learning.springboot.dao.mapper"})
//定时任务
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    //extends SpringBootServletInitializer 重写configure方法，才能正常部署到tomcat中
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
