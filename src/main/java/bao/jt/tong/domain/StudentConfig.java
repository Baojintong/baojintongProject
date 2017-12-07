package bao.jt.tong.domain;

import bao.jt.tong.condition.MyCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Conditional(MyCondition.class)
public class StudentConfig {

    //声明一个bean
    @Bean
    public Book sss(){
        Book b=new Book(1,"java核心技术",new BigDecimal(20),"");
        System.out.println(b);
        return b;
    }
}
