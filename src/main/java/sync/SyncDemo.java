package sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author likelong
 * @date 2022/10/5
 */
public class SyncDemo {

    public synchronized void add() {
        add();
    }

    public static void main(String[] args) {

        // 例1：
//        Object o = new Object();
//        new Thread(() -> {
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName() + " :: " + "外层");
//                synchronized (o) {
//                    System.out.println(Thread.currentThread().getName() + " :: " + "中层");
//                    synchronized (o) {
//                        System.out.println(Thread.currentThread().getName() + " :: " + "内层");
//                    }
//                }
//            }
//        }, "AA").start();

        // 例2：Exception in thread "main" java.lang.StackOverflowError
//        new SyncDemo().add();

        // Lock 演示可重入锁
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " :: " + "外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " :: " + "内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("aaaa");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
