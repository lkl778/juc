package tools;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /*
          集齐7颗龙珠召唤神龙
         */
        //召唤神龙的线程
        CyclicBarrier barrier = new CyclicBarrier(7, () -> System.out.println("成功召唤神龙！"));

        for (int i = 1; i <= 7; i++) {
            final int temp = i;

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集了" + temp + "个龙珠");

                try {
                    barrier.await(); //等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
