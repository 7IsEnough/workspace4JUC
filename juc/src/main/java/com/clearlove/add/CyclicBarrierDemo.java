package com.clearlove.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author promise
 * @date 2022/7/30 - 21:48
 */
public class CyclicBarrierDemo {

  public static void main(String[] args) {
    // 集齐7颗龙珠召唤神龙
    // 召唤龙珠的线程
    CyclicBarrier cyclicBarrier =
        new CyclicBarrier(
            7,
            () -> {
              System.out.println("召唤神龙成功");
            });
    for (int i = 1; i <= 7; i++) {
      // lambda 是访问不到 i 的，需要一个中间变量
      final int temp = i;
      new Thread(
          () -> {
            System.out.println(Thread.currentThread().getName() + "收集" + temp + "个龙珠");
            try {
              // 等待
              cyclicBarrier.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            } catch (BrokenBarrierException e) {
              e.printStackTrace();
            }
          }).start();
    }
  }
}
