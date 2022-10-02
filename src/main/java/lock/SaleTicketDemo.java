package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.Li
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        //并发：多个线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

/**
 * 资源类
 * <p>
 * Lock三部曲
 * 1.new ReentrantLock();
 * 2.lock.lock(); //加锁
 * 3.finally  -》 lock.unlock();//解锁
 */
class Ticket {
    /**
     * 属性
     */
    private int num = 30;

    /**
     * 创建可重入锁
     */
    Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock(); //加锁
        try {
            //业务代码
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (num--) + "张票，剩余" + num + "张");
            }
        } finally {
            lock.unlock();//解锁
        }
    }

}
