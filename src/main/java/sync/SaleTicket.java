package sync;

/**
 * 第一步：创建资源类，定义属性和操作方法
 *
 * @author likelong
 * @date 2022/10/2
 */
class Ticket {
    /**
     * 属性：票数
     */
    private int num = 30;

    /**
     * 操作方案：买票
     */
    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "：卖出：" + (num--) + " 剩下：" + num);
        }
    }
}

/**
 * 创建多个线程，调用资源类里操作方法
 *
 * @author likelong
 */
public class SaleTicket {

    public static void main(String[] args) {
        // 创建资源类
        Ticket ticket = new Ticket();

        // 创建三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 30; j++) {
                    ticket.sale();
                }
            }, i + "").start();
        }
    }

}
