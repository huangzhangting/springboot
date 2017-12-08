package nom.learning.springboot.service.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by huangzhangting on 2017/12/8.
 */
@Slf4j
public class ConsumerTest implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
        for(MessageExt ext : messages){
            String msg = new String(ext.getBody());
            log.info("consume message: {}", msg);
        }
        //TODO 遗留问题，消费后消息始终存在，每次启动都会消费到之前的消息，待跟进
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
