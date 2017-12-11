package nom.learning.springboot.service.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import nom.learning.springboot.dao.model.UserExtendDO;
import nom.learning.springboot.service.mq.rocketmqclient.SimpleProducer;
import nom.learning.springboot.service.mq.rocketmqclient.SimplePushConsumer;
import nom.learning.springboot.service.mq.rocketmqimpl.AbstractMessageListenerConcurrently;
import nom.learning.springboot.service.mq.rocketmqimpl.ProcessMessageException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huangzhangting on 2017/12/11.
 */
@Slf4j
//@Component
@Configuration
public class UserRegisterMQ {
    private static final String TOPIC = "hzt_test_topic";


    @ConfigurationProperties(prefix = "rocketmq")
    @Bean(name = "userRegisterProducer")
    public SimpleProducer userRegisterProducer(){
        SimpleProducer producer = new SimpleProducer();
        producer.setTopic(TOPIC);
        return producer;
    }


    @ConfigurationProperties(prefix = "rocketmq")
    @Bean(name = "userRegisterConsumer")
    public SimplePushConsumer userRegisterConsumer(){
        SimplePushConsumer consumer = new SimplePushConsumer();
        consumer.setTopic(TOPIC);
        consumer.setMessageListener(new AbstractMessageListenerConcurrently<UserExtendDO>() {
            @Override
            protected void processMessage(UserExtendDO message) throws ProcessMessageException {
                if(message == null){
                    throw new ProcessMessageException("message is null.");
                }
                log.info("process message success. message: {}", JSON.toJSONString(message));
//                throw new ProcessMessageException("test process exception.");
            }
        });
        return consumer;
    }

}
