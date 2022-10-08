package cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mr.Li
 */
public class Spinlock {

    // int 0
    // Thread null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ==> myLock");

        //自旋锁
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    //解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ==> myUnLock");

        atomicReference.compareAndSet(thread, null);
    }
}
