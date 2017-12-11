package nom.learning.springboot.service.mq.rocketmqimpl;

/**
 * Created by huangzhangting on 2017/12/11.
 */
public class ProcessMessageException extends RuntimeException {
    public ProcessMessageException(String message) {
        super(message);
    }

    public ProcessMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessMessageException(Throwable cause) {
        super(cause);
    }
}
