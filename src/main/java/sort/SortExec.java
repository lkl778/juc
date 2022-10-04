package sort;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ABC三个线程分别输出A、B、C顺序执行
 *
 * @author likelong
 * @date 2022/10/4
 */
public class SortExec {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            try {
                shareResource.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(() -> {
            try {
                shareResource.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-2").start();

        new Thread(() -> {
            try {
                shareResource.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-3").start();
    }
}

class ShareResource {

    private final Lock lock = new ReentrantLock();
    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    /**
     * 标志位 1打印A 2打印B 3打印C
     */
    private int flag = 1;

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            // 防止虚假唤醒
            while (flag != 1) {
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->A");
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            // 防止虚假唤醒
            while (flag != 2) {
                c2.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->B");
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            // 防止虚假唤醒
            while (flag != 3) {
                c3.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->C");
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}
