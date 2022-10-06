package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author likelong
 * @date 2022/10/6
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {
        // 实现Runnable接口创建线程
        //new Thread(new MyThread1()).start();


        /*
        本身Callable接口和Runnable接口毫无关系
        通过一个Runnable接口的实现类FutureTask，Callable接口与Runnable接口构建了关系，便可以启动线程
         */

        //适配类   FutureTask 是 Runnable接口的实现类   构造器 FutureTask(Callable<V> callable)
        MyThread2 t1 = new MyThread2();
        FutureTask<Integer> futureTask = new FutureTask<>(t1); //泛型是线程返回值类型

        // 任务是否完成
//        while (!futureTask.isDone()) {
//            System.out.println("wait...");
//        }
        /*
        启动两个线程，只会打印一个`call()...`
         */
        new Thread(futureTask, "A").start(); //怎么启动Callable
        new Thread(futureTask, "B").start(); //结果会被缓存，效率高

        Integer i = futureTask.get(); //获取线程返回值  get()可能会产生阻塞！把他放到最后 或者 使用异步通信来处理！

        System.out.println(i);
    }
}

/**
 * 实现Runnable接口
 */
class MyThread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("...run");
    }
}

/**
 * 实现Callable接口
 */
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName() + " call()...");
        return 1024;
    }
}
