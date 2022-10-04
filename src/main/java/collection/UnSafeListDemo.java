package collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author likelong
 * @date 2022/10/4
 */
public class UnSafeListDemo {
    public static void main(String[] args) {

        //并发下 ArrayList不安全
        /*
        解决方案
        1、List<String> list = new Vector<>();
        2、List<String> list = Collections.synchronizedList(new ArrayList<>());
        3、List<String> list = new CopyOnWriteArrayList<>();
         */

        //CopyOnWrite 写入时复制  COW 计算机程序设计领域的一种优化策略；
        //多个线程操作的时候 list 读取的时候 固定的（原本的） 写入（覆盖）
        //再写入的时候避免覆盖，造成数据问题！
        //读写分离
        //CopyOnWriteArrayList 比 Vector 好在哪里？
        //Vector 底层是synchronized实现效率较低 ； CopyOnWriteArrayList 底层是ReentrantLock实现 效率更高 灵活性也更高

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
