package bao.jt.tong.zookeeper;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/*
* 使用异步API创建一个节点
* */
public class TestZookeeper implements Watcher{
    private static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper=
                new ZooKeeper("localhost:2181",5000,
                        new TestZookeeper());

        countDownLatch.await();
        String path1=zooKeeper.create("/zk-baojintong","baojintong".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //在这里如果创建临时性节点，创建完成后在客户端命令行是查不到的，如果想要能够查到，那么需要创建持久性节点
        System.out.println("创建成功"+path1);
        String path2=zooKeeper.create("/zk-baojintong","baojintong".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("创建成功"+path2);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
