package com.clearlove.lock;

/**
 * @author promise
 * @date 2022/8/8 - 21:23
 * synchronized
 */
public class Demo01 {

  public static void main(String[] args) {
    Phone phone = new Phone();

    new Thread(() -> {
      phone.sms();
    }, "A").start();

    new Thread(() -> {
      phone.sms();
    }, "B").start();
  }
}

class Phone {

  public synchronized void sms() {
    System.out.println(Thread.currentThread().getName() + "sms");
    // 这里也有锁
    call();
  }

  public synchronized void call() {
    System.out.println(Thread.currentThread().getName() + "call");
  }

}
