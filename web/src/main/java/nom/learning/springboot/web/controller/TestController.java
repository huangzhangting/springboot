package nom.learning.springboot.web.controller;

import lombok.extern.slf4j.Slf4j;
import nom.learning.springboot.service.UserService;
import nom.learning.springboot.service.bo.User;
import nom.learning.springboot.web.common.ApplicationProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhangting on 2017/12/5.
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    ApplicationProperties properties;
    @Resource
    UserService userService;
    @Resource
    ThreadPoolTaskExecutor taskExecutor;


    @RequestMapping("hello")
    public String sayHello(){
        return "hello world";
    }

    @RequestMapping("users")
    public Object users(){
        List<User> users = userService.userList();

        log.info("get users success");

        return users;
    }

    @RequestMapping("task")
    public Object testTask(){
        taskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                log.info("task run success");
            }
        });
        return true;
    }


    @RequestMapping("mq")
    public Object testMq(){
        userService.register();
        return true;
    }

}
