package nom.learning.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by huangzhangting on 2017/12/5.
 */
@RunWith(SpringRunner.class)

public class ApplicationTest {
    @Test
    public void contextLoads(){
        System.out.println("context loads");
    }
}
