package com.clearlove.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/8/3 - 23:37 Executors 工具类 3大方法 使用了线程池后，使用线程池来创建线程
 */
public class Demo01 {
  public static void main(String[] args) {
    // 单一线程的线程池
    //    ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
    // 固定大小的线程池
    //    ExecutorService threadPool2 = Executors.newFixedThreadPool(5);
    // 可伸缩的，遇强则强，遇弱则弱
    //    ExecutorService threadPool3 = Executors.newCachedThreadPool();

    // 自定义线程池，工作中使用 ThreadPoolExecutor
    // 最大线程数到底该如何定义
    // 1.CPU 密集型 CPU有多少核，就是多少，保持CPU的效率最高
    // 2.IO 密集型  判断你程序中十分耗IO的线程，大于这个数即可
    //       程序  有15个大型任务  IO十分占用

    // 获取当前服务器CPU核数
    System.out.println(Runtime.getRuntime().availableProcessors());

    ExecutorService threadPool =
        new ThreadPoolExecutor(
            2,
            Runtime.getRuntime().availableProcessors(),
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            Executors.defaultThreadFactory(),
            new DiscardOldestPolicy()); // 队列满了，尝试去和最早的竞争，也不会抛出异常

    //            new DiscardPolicy()); // 队列满了，丢掉任务，不会抛出异常
    //            new CallerRunsPolicy());  // 哪来的去哪里  本场景，该线程是由main线程指引的，最终由main线程执行
    //            new AbortPolicy()); // 银行满了，还有人进来，不处理这个人的，抛出异常

    try {
      // 最大承载：队列大小 + max
      // 超过 RejectedExecutionException
      for (int i = 1; i <= 9; i++) {
        int temp = i;
        threadPool.execute(
            () -> {
              System.out.println(Thread.currentThread().getName() + " " + temp + " ok");
            });
        //        threadPool1.execute(
        //            () -> {
        //              System.out.println(Thread.currentThread().getName() + " ok");
        //            });

        //        threadPool2.execute(
        //            () -> {
        //              System.out.println(Thread.currentThread().getName() + " ok");
        //            });

        //        threadPool3.execute(
        //            () -> {
        //              System.out.println(Thread.currentThread().getName() + " ok");
        //            });
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 线程池用完，程序结束，关闭线程池
      //      threadPool1.shutdown();
      //      threadPool2.shutdown();
      //      threadPool3.shutdown();
      threadPool.shutdown();
    }
  }
}
