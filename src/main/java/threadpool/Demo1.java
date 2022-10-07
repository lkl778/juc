package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 工具类：创建线程池  3大方法
 */
public class Demo1 {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();//创建单个线程的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建固定线程的线程池
        //ExecutorService threadPool = Executors.newCachedThreadPool();//创建可伸缩线程池

        try {
            for (int i = 0; i < 10; i++) {
                //使用了线程池之后，用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }

    }

}
