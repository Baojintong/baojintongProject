package bao.jt.tong;


import bao.jt.tong.domain.HelloAutoConfiguration;
import bao.jt.tong.domain.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)//开启Spring集成测试支持
@ContextConfiguration(classes = HelloAutoConfiguration.class)//指定上下文配置
@SpringBootTest
public class TestMethod {
    @Autowired
    private HelloService he;

    @Test
    public void run(){
        System.out.println(he.say());//hello default
    }
}
