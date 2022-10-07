package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author likelong
 * @date 2022/10/7
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        //没有返回值的 runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " runAsync => Void");
        });

        completableFuture.get(); //阻塞，获取执行结果
        System.out.println("11111");

    }
}
