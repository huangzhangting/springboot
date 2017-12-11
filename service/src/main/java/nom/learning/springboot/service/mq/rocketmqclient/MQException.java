package nom.learning.springboot.service.mq.rocketmqclient;

/**
 * Created by huangzhangting on 2017/12/8.
 */
public class MQException extends Exception {
    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQException(Throwable cause) {
        super(cause);
    }
}
