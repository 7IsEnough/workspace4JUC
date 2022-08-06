package com.clearlove.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author promise
 * @date 2022/8/6 - 13:59
 */
public class Test {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // test1();  // 6561
    // test2();  // 4091
    test3();     // 168
  }

  // 普通程序员
  public static void test1() {
    Long sum = 0L;
    long start = System.currentTimeMillis();
    for (Long i = 0L; i <= 10_0000_0000; i++) {
      sum += i;
    }
    long end = System.currentTimeMillis();
    System.out.println("sum=" + sum + " 时间：" + (end - start));
  }

  public static void test2() throws ExecutionException, InterruptedException {
    long start = System.currentTimeMillis();
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
    // 执行任务，但没有返回值，同步操作
    // forkJoinPool.execute(task);

    // 提交任务，有返回值，异步操作
    ForkJoinTask<Long> submit = forkJoinPool.submit(task);
    Long sum = submit.get();

    long end = System.currentTimeMillis();
    System.out.println("sum=" + sum + " 时间：" + (end - start));
  }

  public static void test3() {
    long start = System.currentTimeMillis();
    // Stream 并行流
    // LongStream.range() // 左闭右开 [)
    // LongStream.rangeClosed() // 左闭右闭 []
    long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);

    long end = System.currentTimeMillis();
    System.out.println("sum=" + sum + " 时间：" + (end - start));
  }
}
