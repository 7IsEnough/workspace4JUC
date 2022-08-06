package com.clearlove.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/8/6 - 23:18
 */
public class JMMDemo {

  private static int num = 0;

  // 主线程
  public static void main(String[] args) {

    // 线程1
    new Thread(
            () -> {
              while (num == 0) {}
            })
        .start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    num = 1;

    System.out.println(num);
  }
}
