package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likelong
 * @date 2022/10/4
 */
public class UnSafeMapDemo {
    public static void main(String[] args) {
        /*
        并发下 HashMap线程不安全

        解决方案：
        1.Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
        2.Map<String, Object> map = new ConcurrentHashMap<>();
         */

        //HashMap<String, Object> map = new HashMap<>();

        //Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, Object> map = new ConcurrentHashMap<>(30);
        //加载因子，初始容量

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
