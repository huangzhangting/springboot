package nom.learning.springboot.service.mq;

import com.alibaba.fastjson.JSON;
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
        log.info("consume messages: {}", JSON.toJSONString(messages));
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
