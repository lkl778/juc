package queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * SynchronousQueue 和 BlockingQueue 不一样，SynchronousQueue 不存储元素
 * put一个元素，必须从里面take取出来，否则不能再put进去值！（存一个，取一个，循环）
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        //存一个元素，取一个元素 循环
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "->put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "->put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "->put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->take " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
