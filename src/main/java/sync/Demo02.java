package sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.Li
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();

    }
}

class Phone2 {
    private final Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock(); //Lock锁必须配对，有加锁就必须有解锁！ 否则就会死锁！
        try {
            System.out.println(Thread.currentThread().getName() + " sms");
            call();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " call");
        } finally {
            lock.unlock();
        }
    }
}
