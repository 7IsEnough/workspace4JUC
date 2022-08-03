package com.clearlove.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author promise
 * @date 2022/8/3 - 23:37 Executors 工具类 3大方法 使用了线程池后，使用线程池来创建线程
 */
public class Demo01 {
  public static void main(String[] args) {
    // 单一线程的线程池
    ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
    // 固定大小的线程池
    ExecutorService threadPool2 = Executors.newFixedThreadPool(5);
    // 可伸缩的，遇强则强，遇弱则弱
    ExecutorService threadPool3 = Executors.newCachedThreadPool();

    try {
      for (int i = 0; i < 10; i++) {
//        threadPool1.execute(
//            () -> {
//              System.out.println(Thread.currentThread().getName() + " ok");
//            });

//        threadPool2.execute(
//            () -> {
//              System.out.println(Thread.currentThread().getName() + " ok");
//            });

        threadPool3.execute(
            () -> {
              System.out.println(Thread.currentThread().getName() + " ok");
            });
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 线程池用完，程序结束，关闭线程池
      threadPool1.shutdown();
      threadPool2.shutdown();
      threadPool3.shutdown();
    }
  }
}
