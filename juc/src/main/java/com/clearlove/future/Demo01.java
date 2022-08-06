package com.clearlove.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author promise
 * @date 2022/8/6 - 15:46
 * 异步调用 CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo01 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 没有返回值的 runAsync 异步回调
//    CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
//      try {
//        TimeUnit.SECONDS.sleep(2);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//      System.out.println(Thread.currentThread().getName() + " runAsync=>Void");
//    });
//
//    System.out.println("1111");

    // 获取阻塞执行结果
//    completableFuture.get();


    // 有返回值的 supplyAsync 异步回调
    // 成功回调  返回值
    // 失败回调  返回错误信息
    CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getName() + " supplyAsync=>Integer");
      int i = 10 / 0;
      return 1024;
    });

    System.out.println(
        completableFuture
            .whenComplete(
                (t, u) -> {
                  // t：正常的返回结果  u：有异常就是错误信息
                  System.out.println("t=>" + t);
                  System.out.println("u=>" + u);
                })
            .exceptionally(
                (e) -> {
                  System.out.println(e.getMessage());
                  // 执行异常时，可以获取到错误的返回结果
                  return 233;
                })
            .get());
  }
}
