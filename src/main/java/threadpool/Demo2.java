package threadpool;

import java.util.concurrent.*;

/**
 * Executors 工具类：创建线程池  3大方法
 *
 * 4大拒绝策略：
 * new ThreadPoolExecutor.AbortPolicy() //默认拒绝策略 银行满了，还有人进来，不处理这个人，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy() //哪来的去哪里！
 * new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试和最早的竞争，也不会抛出异常！
 */
public class Demo2 {
    public static void main(String[] args) {
        //工具类创建
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//创建单个线程的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建固定线程的线程池
        //ExecutorService threadPool = Executors.newCachedThreadPool();//创建可伸缩线程池

        //手动创建线程池 ThreadPoolExecutor
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试和最早的竞争，也不会抛出异常！

        try {
            //最大承载：Queue + max
            //超出最大承载 RejectedExecutionException （默认拒绝策略）
            for (int i = 0; i < 9; i++) {
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
