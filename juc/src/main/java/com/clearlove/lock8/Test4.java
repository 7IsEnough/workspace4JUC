package com.clearlove.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/7/24 - 15:29
 * 7. 一个静态同步方法，一个普通同步方法，一个对象，先执行 发短信还是打电话?  打电话
 * 8. 一个静态同步方法，一个普通同步方法，两个对象，先执行 发短信还是打电话?  打电话
 */
public class Test4 {

  public static void main(String[] args) {
    // 两个对象,两个调用者，两把锁
    // 两个对象的Class类模板只有一个，static，锁的是class
    Phone4 phone1 = new Phone4();
    Phone4 phone2 = new Phone4();

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

class Phone4 {

  // synchronized 锁的对象是方法的调用者
  // 两个方法用的是同一个锁，谁先拿到，谁执行
  // static 静态方法
  // 类一加载就有了！锁的是Class
  public static synchronized void sendSms() {
    try {
      TimeUnit.SECONDS.sleep(4);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("发短信");
  }

  // 普通同步方法
  // 锁的是 调用者
  public synchronized void call() {
    System.out.println("打电话");
  }


}

