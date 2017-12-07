package bao.jt.tong.condition;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@org.springframework.stereotype.Service
public class Thread1 extends Thread {

    private List<ConcurrentHashMap<String, String>> list;

    private ConcurrentHashMap<String, String> cm;

    private Thread2 t2 = new Thread2();

    public void setCm(ConcurrentHashMap<String, String> cm) {
        this.cm = cm;
    }

    public List<ConcurrentHashMap<String, String>> getList() {
        return list;
    }

    public void setList(List<ConcurrentHashMap<String, String>> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (ConcurrentHashMap cm : list) {
            if ("男".equals(cm.get("sex"))) {
                System.out.println(cm.get("num") + "---" + Thread.currentThread().getName());
            } else {
                t2.addCm(cm);
                t2.run();
            }
        }
    }
}
