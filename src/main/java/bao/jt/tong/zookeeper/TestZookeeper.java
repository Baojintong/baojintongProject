package bao.jt.tong.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestZookeeper{
    static String path="/zk-book/ybjk3";
    static RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,3);
    static CountDownLatch semaphore=new CountDownLatch(2);
    static ExecutorService tp= Executors.newFixedThreadPool(2);
    static CuratorFramework client= CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .build();
    public static void main(String[] args) throws Exception {
        client.start();
        final PathChildrenCache cache=new PathChildrenCache(client,path,true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(
                new PathChildrenCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                        switch (pathChildrenCacheEvent.getType()){
                            case CHILD_ADDED:
                                System.out.println("CHILD_ADDED,"+pathChildrenCacheEvent.getData().getPath());
                                break;
                            case CHILD_UPDATED:
                                System.out.println("CHILD_UPDATED,"+pathChildrenCacheEvent.getData().getPath());
                                break;
                            case CHILD_REMOVED:
                                System.out.println("CHILD_REMOVED,"+pathChildrenCacheEvent.getData().getPath());
                                break;
                            default:break;
                        }
                    }
                }
        );
        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        Thread.sleep(1000);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path+"/c1");
        Thread.sleep(1000);
        client.delete().forPath(path+"/c1");
//        输出
//        CHILD_ADDED,/zk-book/ybjk3/c1
//        CHILD_REMOVED,/zk-book/ybjk3/c1
    }
}
