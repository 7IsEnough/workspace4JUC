package com.clearlove.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author promise
 * @date 2022/8/8 - 21:23 synchronized
 */
public class Demo02 {

  public static void main(String[] args) {
    Phone2 phone = new Phone2();

    new Thread(
            () -> {
              phone.sms();
            },
            "A")
        .start();

    new Thread(
            () -> {
              phone.sms();
            },
            "B")
        .start();
  }
}

class Phone2 {

  Lock lock = new ReentrantLock();

  public void sms() {
    // 细节问题
    // lock 锁必须配对，否则就会死锁
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + "sms");
      // 这里也有锁
      call();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void call() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + "call");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
