package nom.learning.springboot.service.mq.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Created by huangzhangting on 2017/12/8.
 */
@Slf4j
public class SimpleProducer implements InitializingBean, DisposableBean {
    @Setter
    private long defaultTimeout = 3000;//默认3秒
    @Setter
    private String nameServerAddress;
    @Setter
    private String application;

    @Setter
    private String topic;

    private DefaultMQProducer producer;

    static {
        LogInit.changeRocketMQLogHome();
    }

    /**
     * 发送消息，使用默认topic和超时时间
     *
     * @param message
     * @return
     * @throws MQException
     */
    public SendResult sendMessage(StringMessage message) throws MQException {
        return sendMessage(this.topic, message, defaultTimeout);
    }

    /**
     * 发送消息，使用默认topic
     *
     * @param message
     * @param timeoutInMilliSeconds
     * @return
     * @throws MQException
     */
    public SendResult sendMessage(StringMessage message, long timeoutInMilliSeconds) throws MQException {
        return sendMessage(this.topic, message, timeoutInMilliSeconds);
    }

    /**
     * 发送消息，使用默认超时时间
     *
     * @param topic 指定topic，不指定使用sendMessage(StringMessage message)方法
     * @param message
     * @return
     * @throws MQException
     */
    public SendResult sendMessage(String topic, StringMessage message) throws MQException {
        return sendMessage(topic, message, defaultTimeout);
    }

    /**
     * 发送消息
     * @param topic 指定topic，不指定使用sendMessage(StringMessage message, long timeoutInMilliSeconds)方法
     * @param message
     * @param timeoutInMilliSeconds
     * @return
     * @throws MQException
     */
    public SendResult sendMessage(String topic, StringMessage message, long timeoutInMilliSeconds) throws MQException {
        Assert.notNull(topic);
        Assert.notNull(message);
        Assert.notNull(message.getBody());
        Assert.isTrue(timeoutInMilliSeconds > 0, "超时时间必须大于0");
        try {
            Message msg = new Message(topic, message.getTags(), message.getKeys(), message.getBody().getBytes());
            return producer.send(msg, timeoutInMilliSeconds);
        } catch (Exception e) {
            throw new MQException(e);
        }
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    @Override
    public void destroy() throws Exception {
        try {
            producer.shutdown();
        } finally {
            log.warn("SimpleProducer called shutdown.");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(nameServerAddress);
        Assert.hasText(application);
        //Assert.hasText(topic);

        producer = new DefaultMQProducer(this.application);
        producer.setNamesrvAddr(this.nameServerAddress);
        producer.setVipChannelEnabled(false);
        producer.start();
    }
}
