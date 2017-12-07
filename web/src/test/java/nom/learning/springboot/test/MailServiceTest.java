package nom.learning.springboot.test;

import nom.learning.springboot.service.MailService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by huangzhangting on 2017/12/6.
 */
public class MailServiceTest extends BaseTest {
    private static String to = "15158036445@163.com";

    @Resource
    MailService mailService;

    @Test
    public void test_send(){
        mailService.send(to, "spring boot mail test", "spring boot mail test");
    }

    @Test
    public void test_send_html(){
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";

        mailService.sendHtmlMail(to, "html mail", content);
    }

    @Test
    public void test_sendAttachmentsMail(){
        String filePath = "C:\\Users\\huangzhangting\\Desktop\\新建文件夹\\8f5920949dd167fce8224031faeee829.jpg";
        mailService.sendAttachmentsMail(to, "Attachments Mail", "Attachments Mail", filePath);
    }

    @Test
    public void test_sendInlineResourceMail() {
        String rscId = "img1";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\huangzhangting\\Desktop\\新建文件夹\\8f5920949dd167fce8224031faeee829.jpg";

        mailService.sendInlineResourceMail(to, "Inline Resource Mail", content, imgPath, rscId);
    }

}
