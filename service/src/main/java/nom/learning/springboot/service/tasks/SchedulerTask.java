package nom.learning.springboot.service.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by huangzhangting on 2017/12/6.
 */
@Slf4j
@Component
public class SchedulerTask {

//    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        log.info("process task");
    }

}
