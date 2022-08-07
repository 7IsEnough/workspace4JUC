package com.clearlove.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author promise
 * @date 2022/8/7 - 11:14
 * 不保证原子性
 */
public class VDemo02 {

  // volotile 不能保证原子性
//  private volatile static int num = 0;
  // 原子类的Integer
  private volatile static AtomicInteger num = new AtomicInteger();

  public static void add() {
    // 不是一个原子性操作
//    num++;

    // AtomicInteger + 1 方法  CAS
    num.getAndIncrement();
  }

  public static void main(String[] args) {

    // 理论上num结果为20000
    for (int i = 1; i <= 20; i++) {
      new Thread(() -> {
        for(int j = 0; j < 1000; j++) {
          add();
        }
      }).start();
    }

    // 如果有超过2个线程还在执行，则认为还没执行完
    // 默认2个线程 main gc
    while (Thread.activeCount() > 2) {
      Thread.yield();
    }

    System.out.println(Thread.currentThread().getName() + " " + num);
  }
}
