package forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 * <p>
 * 如何使用 ForkJoin?
 * 1.ForkJoinPool 通过它来执行
 * 2.计算任务 forkJoinPool.execute(ForkJoinTask<?> task)
 * 3.计算类要继承ForkJoinTask
 *
 * @author Mr.Li
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    //临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 计算方法
     *
     * @return 计算结果
     */
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //分支合并计算
            //中间值
            Long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); //拆分任务，把线程任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle, end);
            task2.fork(); //拆分任务，把线程任务压入线程队列

            // 结果汇总
            return task1.join() + task2.join();
        }
    }
}
