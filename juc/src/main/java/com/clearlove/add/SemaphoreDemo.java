package com.clearlove.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/7/31 - 10:56
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    // 线程数量  停车位  限流
    Semaphore semaphore = new Semaphore(3);
    for (int i = 1; i <= 6; i++) {
      new Thread(
              () -> {
                // acquire() 得到
                try {
                  semaphore.acquire();
                  System.out.println(Thread.currentThread().getName() + "抢到车位");
                  TimeUnit.SECONDS.sleep(2);
                  System.out.println(Thread.currentThread().getName() + "离开车位");

                } catch (InterruptedException e) {
                  e.printStackTrace();
                } finally {
                  // release() 释放
                  semaphore.release();
                }
              }, String.valueOf(i))
          .start();
    }
  }
}
