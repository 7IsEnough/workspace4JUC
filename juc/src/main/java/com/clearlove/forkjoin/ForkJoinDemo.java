package com.clearlove.forkjoin;

import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author promise
 * @date 2022/8/6 - 10:49
 * 求和计算的任务
 * 如何使用forkJoin
 * 1. 通过forkJoinPool来执行
 * 2. 计算任务forkJoinPool.execute(ForkJoinTask<> task)
 * 3. 计算类要继承 ForkJoinTask
 */
@Data
@AllArgsConstructor
public class ForkJoinDemo extends RecursiveTask<Long> {

  private Long start; // 1

  private Long end; // 1990900000

  // 临界值
  private Long temp = 10000L;

  public ForkJoinDemo(Long start, Long end) {
    this.start = start;
    this.end = end;
  }

  // 计算方法
  @Override
  protected Long compute() {
    if (end - start >= temp) { // forkJoin 递归
      long middle = (start + end) / 2;
      ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
      // 拆分任务，把任务压入线程队列
      task1.fork();
      ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
      // 拆分任务，把任务压入线程队列
      task2.fork();

      return task1.join() + task2.join();
    } else {
      Long sum = 0L;
      for (Long i = start; i <= end; i++) {
        sum += i;
      }
      return sum;
    }
  }
}
