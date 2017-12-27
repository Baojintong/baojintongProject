package bao.jt.tong.metrics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ApplicationContextMetrics implements PublicMetrics {

    @Autowired
    private ApplicationContext context;

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new ArrayList<Metric<?>>();
        metrics.add(new Metric<Long>("Spring启动时间",context.getStartupDate()));
        metrics.add(new Metric<Integer>("Bean定义数量",context.getBeanDefinitionCount()));
        metrics.add(new Metric<Integer>("Bean数量",context.getBeanNamesForType(Object.class).length));
        metrics.add(new Metric<Integer>("spring.controllers",context.getBeanNamesForAnnotation(Controller.class).length));
        return metrics;
    }
}
