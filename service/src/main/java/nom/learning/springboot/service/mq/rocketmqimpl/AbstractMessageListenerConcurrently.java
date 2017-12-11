package nom.learning.springboot.service.mq.rocketmqimpl;

import com.alibaba.fastjson.JSON;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by huangzhangting on 2017/12/11.
 */
@Slf4j
public abstract class AbstractMessageListenerConcurrently<T> implements MessageListenerConcurrently {
    @Setter
    private boolean idempotent = false; //幂等的


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        //因为producer一次只发送一条消息 @see SimpleProducer
        MessageExt message = list.get(0);
        /**
         * 消息重复处理
         * 1、如果业务处理支持幂等，直接交给业务处理即可
         * 2、通用的做法，消费日志判断，但是需要每条消息都有唯一的业务主键
         *
         * 下面是消费日志的处理方式
         *
         * */
        String keys = message.getKeys();
        //1、验证消息是否已经消费过

        //2、插入消费日志

        //3、业务处理
        String msg = new String(message.getBody());
        log.info("consume message: {}", msg);
        try {
            T entity = JSON.parseObject(msg, getEntityClass());
            processMessage(entity);
            //4、更新消费成功状态

            log.info("consume message success.");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            log.error("process message exception. message: " + msg, e);
        }
        //4、更新消费失败状态

        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }

    @SuppressWarnings("unchecked")
    //获取泛型的class
    private Class<T> getEntityClass(){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class <T> entityClass = (Class <T>) type.getActualTypeArguments()[0];
        return entityClass;
    }


    //业务处理
    protected abstract void processMessage(T message) throws ProcessMessageException;

}
