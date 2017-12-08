package nom.learning.springboot.service.mq.rocketmq;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangzhangting on 2017/12/8.
 */
@Data
public class StringMessage implements Serializable{
    private String body;
    private String keys;
    private String tags;

    public StringMessage(String body) {
        this.body = body;
    }

    public StringMessage(String body, String keys) {
        this.body = body;
        this.keys = keys;
    }

    public StringMessage(String body, String keys, String tags) {
        this.body = body;
        this.keys = keys;
        this.tags = tags;
    }

}
