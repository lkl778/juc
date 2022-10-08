package cas;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {
    public static void main(String[] args) {
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        lock.unlock();

        // 底层使用的自旋锁CAS
        Spinlock spinlock = new Spinlock();

        new Thread(() -> {
            spinlock.myLock();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlock.myUnLock();
            }
        }, "T1").start();

        new Thread(() -> {
            spinlock.myLock();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlock.myUnLock();
            }

        }, "T2").start();

    }
}
