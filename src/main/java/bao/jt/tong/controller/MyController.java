package bao.jt.tong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CounterService counterService;//定义了三个方法，分别用来增加、减少或重置特定名称的度量值

    private GaugeService gaugeService;//将某个值记录到特定名称的度量值里

    @Autowired
    public MyController(CounterService counterService,GaugeService gaugeService) {
        this.counterService=counterService;
        this.gaugeService=gaugeService;
    }
    //统计该接口的调用次数
    @RequestMapping("/con")
    public String con(){
        counterService.increment("调用次数:");
        gaugeService.submit("调用时间",System.currentTimeMillis());
        return "true";
    }
}
