package com.clearlove.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author promise
 * @date 2022/7/22 - 20:41
 */
public class SaleTicketDemo02 {

  public static void main(String[] args) {
    // 多线程操作
    // 并发：多个线程操作同一个资源类，把资源类丢入线程
    Ticket2 ticket = new Ticket2();

    new Thread(
            () -> {
              for (int i = 0; i < 40; i++) {
                ticket.sale();
              }
            },
            "A")
        .start();
    new Thread(
            () -> {
              for (int i = 0; i < 40; i++) {
                ticket.sale();
              }
            },
            "B")
        .start();
    new Thread(
            () -> {
              for (int i = 0; i < 40; i++) {
                ticket.sale();
              }
            },
            "C")
        .start();
  }
}

/** Lock 三部曲 1. new ReentrantLock(); 2. lock.lock(); 加锁 3. finally => lock.unlock(); 解锁 */

// 资源类 OOP
class Ticket2 {
  // 使用Lock锁
  private int number = 30;

  Lock lock = new ReentrantLock();

  public void sale() {

    // 加锁
    lock.lock();

    try {
      // 业务代码
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "卖出了" + number-- + "票，剩余" + number);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 解锁
      lock.unlock();
    }
  }
}
