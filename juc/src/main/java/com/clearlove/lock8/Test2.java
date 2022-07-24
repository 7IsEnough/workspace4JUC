package com.clearlove.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/7/24 - 15:00
 * 3. 增加了一个普通方法，先执行 发短信还是hello?  先hello
 * 4. 两个对象，两个同步方法，先执行 发短信还是打电话?  先打电话
 */
public class Test2 {

  public static void main(String[] args) {
    // 两个对象,两个调用者，两把锁
    Phone2 phone1 = new Phone2();
    Phone2 phone2 = new Phone2();

    new Thread(() -> {
      phone1.sendSms();
    }, "A").start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(() -> {
      phone2.call();
    }, "B").start();
  }
}

class Phone2 {

  // synchronized 锁的对象是方法的调用者
  // 两个方法用的是同一个锁，谁先拿到，谁执行
  public synchronized void sendSms() {
    try {
      TimeUnit.SECONDS.sleep(4);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("发短信");
  }

  public synchronized void call() {
    System.out.println("打电话");
  }

  // 这里没有锁，不是同步方法，不受锁的影响
  public void hello() {
    System.out.println("hello");
  }

}
