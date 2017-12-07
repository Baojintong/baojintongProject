package bao.jt.tong.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by bjt on 2017/11/22.
 */
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass("bao.jt.tong.domain.TestCondition");//声明当存在这个类的时候注入bean
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
