package bao.jt.tong.condition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by BJT on 2017/11/24.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Num u1 = new Num();
        Num u2 = new Num();
        final CountDownLatch latch = new CountDownLatch(10);
//        for (int i=0;i<10;i++) {
//            (new Thread() {
//                @Override
//                public void run() {
//                    for(int j=0;j<1000;j++) {
//                        u1.addAi();
//                    }
//                    latch.countDown();
//                }
//            }).start();
//        }
//        latch.await();
//        System.out.println(u1.getAi());//8020
//        for (int i=0;i<10;i++) {
//            (new Thread() {
//                @Override
//                public void run() {
//                    for(int j=0;j<1000;j++) {
//                        u2.addAt();
//                    }
//                    latch.countDown();
//                }
//            }).start();
//        }
//        latch.await();
//        System.out.println(u2.getAt());//10000

//        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(3);
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor
//                (10,
//                        10, 30,
//                        TimeUnit.SECONDS, queue,new HandlerThreadFactory());//通过工厂设置了异常处理器
//
//        Thread.setDefaultUncaughtExceptionHandler(new defaultUncaughtExceptionHandler());
//        executor.execute(new Run1());//发生了异常  java.lang.RuntimeException
//
//        ThreadPoolExecutor executor2 = new ThreadPoolExecutor
//                (10,
//                        10, 30,
//                        TimeUnit.SECONDS, queue);//未设置异常处理器
//        executor2.execute(new Run1());//默认的异常处理器--发生了异常  java.lang.RuntimeException
        final ThreadLocal<String> tl=new ThreadLocal<String>();
        tl.set("123");
        String a=tl.get();
        tl.remove();
        System.out.println(a);
        new Thread(){//观察该线程和fun1线程之间的数据是否有共享
            public void run() {
                System.out.println(tl.get());
            }
        }.start();


    }
}

class Num {
    private AtomicInteger at = new AtomicInteger(0);

    private Integer ai=0;

    Lock lock=new ReentrantLock();


    public AtomicInteger getAt() {
        return at;
    }

    public void addAt() {
        at.incrementAndGet();
    }
    public Integer getAi() {
        return ai;
    }

    public void addAi() {
        //lock.lock();
        ai++;
        //lock.unlock();
    }
}

class Run1 implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

}

class myUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {//会在线程因未捕获的异常而临近死亡时被调用
        System.out.println("发生了异常  "+e);
    }
}

class defaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {//会在线程因未捕获的异常而临近死亡时被调用
        System.out.println("默认的异常处理器--发生了异常  "+e);
    }
}

class HandlerThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setUncaughtExceptionHandler(new myUncaughtExceptionHandler());//设置异常处理器
        return t;
    }
}


