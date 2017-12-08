package nom.learning.springboot.service.mq.rocketmq;

/**
 * Created by huangzhangting on 2017/12/8.
 */
public class LogInit {

    public static void changeRocketMQLogHome() {
        String catalinaHome = System.getProperty("catalina.home");
        if (catalinaHome != null) {
            if (catalinaHome.startsWith("/data/")) {
                System.setProperty("rocketmq.client.logRoot",
                        "/data/logs/mq/" + catalinaHome.substring("/data/".length()));
            } else {
                System.setProperty("rocketmq.client.logRoot",
                        catalinaHome + (catalinaHome.endsWith("/") ? "logs" : "/logs"));
            }
        }
    }

}
