/**
 * @author likelong
 * @date 2022/9/28
 */
public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");

        // 设置为守护线程
        thread.setDaemon(true);
        thread.start();

        System.out.println(Thread.currentThread().getName() + " over");
    }
}
