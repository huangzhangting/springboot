package nom.learning.springboot.service.mq.rocketmqimpl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import nom.learning.springboot.service.mq.rocketmqclient.MQException;
import nom.learning.springboot.service.mq.rocketmqclient.SimpleProducer;
import nom.learning.springboot.service.mq.rocketmqclient.StringMessage;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;

/**
 * Created by huangzhangting on 2017/12/11.
 */
@Slf4j
public class ProducerUtil {

    public static void sendMessage(SimpleProducer producer, Object message){
        sendMessage(producer, message, null);
    }

    public static void sendMessage(SimpleProducer producer, Object message, String keys){
        if(producer==null || message==null){
            return;
        }
        StringMessage stringMessage = new StringMessage(JSON.toJSONString(message), keys);
        try {
            SendResult sendResult = producer.sendMessage(stringMessage);
            if(sendResult != null && SendStatus.SEND_OK == sendResult.getSendStatus()){
                log.info("mq send message success. message: {}", stringMessage);
                return;
            }
            log.warn("mq send message failed. message: {}", stringMessage);
        } catch (MQException e) {
            log.error("mq send message exception. message: " + stringMessage, e);
            //TODO 失败消息持久化，后续定时任务重发

        }
    }

}
