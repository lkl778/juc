package jmm;

import java.util.concurrent.TimeUnit;

public class JMMDemo {

    // 如果不加volatile 程序会死循环
    // 加了volatile是可以保证可见性的，volatile保证一旦数据被修改，其它线程立马能够感知到
    private volatile static int num = 0;

    public static void main(String[] args) { // main 线程

        new Thread(() -> { // 线程1  不知道主内存中的值发生了变化
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
