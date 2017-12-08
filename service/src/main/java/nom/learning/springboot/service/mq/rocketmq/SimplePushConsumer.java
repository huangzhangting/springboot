package nom.learning.springboot.service.mq.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Created by huangzhangting on 2017/12/8.
 */
@Slf4j
public class SimplePushConsumer implements InitializingBean, DisposableBean {

    @Setter
    private String nameServerAddress;
    @Setter
    private String application;
    @Setter
    private String topic;
    @Setter
    private String tag;

    @Setter
    private boolean broadcasting = false;

    @Setter
    private MessageListener messageListener;

    private DefaultMQPushConsumer pushConsumer;

    static {
        LogInit.changeRocketMQLogHome();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(nameServerAddress);
        Assert.hasText(application);
        Assert.hasText(topic);
        Assert.notNull(messageListener);
        if (!(messageListener instanceof MessageListenerConcurrently) && !(messageListener instanceof MessageListenerOrderly)) {
            throw new IllegalArgumentException("messageListener必须是MessageListenerConcurrently或MessageListenerOrderly类型");
        }

        // http://blog.csdn.net/a417930422/article/details/50663639
        pushConsumer = new DefaultMQPushConsumer(application.toUpperCase() + "_" + topic);
        pushConsumer.setNamesrvAddr(nameServerAddress);
        pushConsumer.subscribe(topic, tag);
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        pushConsumer.setVipChannelEnabled(false);
        if (broadcasting) {
            pushConsumer.setMessageModel(MessageModel.BROADCASTING);
        }
        if (messageListener instanceof MessageListenerConcurrently) {
            pushConsumer.registerMessageListener((MessageListenerConcurrently) messageListener);
        } else {
            pushConsumer.registerMessageListener((MessageListenerOrderly) messageListener);
        }
        pushConsumer.start();

    }

    @Override
    public void destroy() throws Exception {
        try {
            pushConsumer.shutdown();
        } finally {
            log.warn("SimplePushConsumer called shutdown.");
        }
    }
}