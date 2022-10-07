package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 类似异步调用：Ajax
 * <p>
 * 异步调用：CompletableFuture
 * 成功回调
 * 失败回调
 */
public class Demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //有返回值的 supplyAsync 异步回调
        //成功和失败回调
        //返回的是错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync => Integer");
            int i = 10 / 0;
            return 1024;
        });

        //成功回调
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t); //正常的返回结果
            System.out.println("u=>" + u); //异常错误信息 java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e -> {//失败回调
            System.out.println(e.getMessage());
            return 233; // 可以获取到错误的返回结果
        })).get());
    }
}
