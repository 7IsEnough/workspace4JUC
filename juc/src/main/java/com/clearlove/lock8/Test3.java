package com.clearlove.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/7/24 - 15:16
 * 5. 增加两个静态的同步方法，只有一个对象，先打印 发短信还是打电话？  发短信
 * 6. 两个对象！增加两个静态的同步方法，只有一个对象，先打印 发短信还是打电话？ 发短信
 */
public class Test3 {


  public static void main(String[] args) {
    // 两个对象,两个调用者，两把锁
    // 两个对象的Class类模板只有一个，static，锁的是class
    Phone3 phone1 = new Phone3();
    Phone3 phone2 = new Phone3();

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

// Phone3唯一的一个class对象
class Phone3 {

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

  public static synchronized void call() {
    System.out.println("打电话");
  }


}
