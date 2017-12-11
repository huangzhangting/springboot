package nom.learning.springboot.service.mq;

import lombok.extern.slf4j.Slf4j;
import nom.learning.springboot.service.mq.rocketmq.SimpleProducer;
import nom.learning.springboot.service.mq.rocketmq.SimplePushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

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
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                //一次只会消费一条消息
                for(MessageExt ext : messages){
                    String msg = new String(ext.getBody());
                    log.info("consume message: {}", msg);
                }
                //TODO 遗留问题，消费后消息始终存在，每次启动都会消费到之前的消息，待跟进：mq版本问题
                //mq服务使用的是Apache的最新代码编译的，client使用的是Alibaba的，所以ack一直没有成功，client版本调整成Apache的就没有问题了
                log.info("consume success.");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        return consumer;
    }

}
