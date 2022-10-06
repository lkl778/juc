package tools;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 * @author Mr.Li
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //倒计时 起始为6 必须要执行任务的时候，再使用!
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i < 7; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "--->Go out");
                countDownLatch.countDown(); // -1
            }, String.valueOf(i)).start();
        }

        countDownLatch.await(); //等待计数器归零，再向下执行

        System.out.println("Close Door!");
    }
}
