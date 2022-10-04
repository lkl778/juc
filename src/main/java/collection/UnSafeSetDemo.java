package collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author likelong
 * @date 2022/10/4
 */
public class UnSafeSetDemo {
    public static void main(String[] args) {
        //HashSet<String> set = new HashSet<>();
        //解决方案一：
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //解决方案二：
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
