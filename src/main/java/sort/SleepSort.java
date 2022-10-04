package sort;

import java.util.concurrent.TimeUnit;

/**
 * 睡眠排序
 *
 * @author likelong
 * @date 2022/10/4
 */
public class SleepSort {

    public static void main(String[] args) {
        int[] arr = {2, 1, 7, 10, 15, 3};

        for (int i = 0; i < arr.length; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(arr[finalI]);
                    System.out.println(arr[finalI]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
