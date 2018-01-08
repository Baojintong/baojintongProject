package bao.jt.tong.zookeeper;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/*
* 使用异步API删除一个节点
* */
public class TestZookeeper implements Watcher{
    private static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper=
                new ZooKeeper("localhost:2181",5000,
                        new TestZookeeper());
        String path="/zk-baojintong-111";
        countDownLatch.await();
        zooKeeper.create(path,"baojintong1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.delete(path,-1,new IStringCallback(),"context");
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
class IStringCallback implements AsyncCallback.VoidCallback{
    @Override
    public void processResult(int i, String s, Object o) {
        System.out.println(i+"---"+s+"---"+o);//0---/zk-baojintong-111---context
    }
}