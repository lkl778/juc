package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo3 {

    //CAS  compareAndSet：比较并交换！
    public static void main(String[] args) {

        //AtomicStampedReference 泛型如果使用包装类，注意对象引用问题
        //正常在业务操作，这里泛型都是一个个对象
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(() -> {
            int stamp = stampedReference.getStamp(); //获得版本号
            System.out.println(Thread.currentThread().getName() + " 1 -> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(stampedReference.compareAndSet(1, 2,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1));

            System.out.println(Thread.currentThread().getName() + " 2 -> " + stampedReference.getStamp());

            System.out.println(stampedReference.compareAndSet(2, 1,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1));

            System.out.println(Thread.currentThread().getName() + " 3 -> " + stampedReference.getStamp());

        }, "a").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp(); //获得版本号
            System.out.println(Thread.currentThread().getName() + " 1 -> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(stampedReference.compareAndSet(1, 6,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1));

            System.out.println(Thread.currentThread().getName() + " 2 -> " + stampedReference.getStamp());
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("**************");
            System.out.println(stampedReference.getReference());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
