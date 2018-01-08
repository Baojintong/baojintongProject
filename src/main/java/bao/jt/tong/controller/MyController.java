package bao.jt.tong.controller;

import bao.jt.tong.mongodbDao.MongoTraceRepository;
import bao.jt.tong.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MyController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CounterService counterService;//定义了三个方法，分别用来增加、减少或重置特定名称的度量值

    private GaugeService gaugeService;//将某个值记录到特定名称的度量值里

    @Autowired
    private MongoTraceRepository mongoTraceRepository;

    @Autowired
    private TestService testService;

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

    @RequestMapping("/Testtrace")
    public List<Trace> trace(){
//        HashMap hashMap=new HashMap();
//        hashMap.put("name","bjt");
//        hashMap.put("age","22");
//        mongoTraceRepository.add(hashMap);
        return mongoTraceRepository.findAll();
    }
    @RequestMapping("/fun1")
    public void fun1(){
        testService.fun("fun1");
    }
    @RequestMapping("/fun2")
    public void fun2(){
        testService.fun("fun2");
    }
}
