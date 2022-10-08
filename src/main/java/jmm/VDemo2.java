package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 * @author Mr.Li
 */
public class VDemo2 {

    // volatile 不保证原子性
    // private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //num++;
        num.getAndIncrement();
    }

    public static void main(String[] args) {

        // 20个线程，每个线程调用100次  理论值 2万
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { // main gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
