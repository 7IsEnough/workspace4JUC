package com.clearlove.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/8/8 - 22:26
 */
public class DeadLockDemo {

  public static void main(String[] args) {

    String lockA = "lockA";
    String lockB = "lockB";

    new Thread(new MyThread(lockA, lockB),"T1").start();
    new Thread(new MyThread(lockB, lockA),"T2").start();

  }
}

class MyThread implements Runnable {

  private String lockA;
  private String lockB;

  public MyThread(String lockA, String lockB) {
    this.lockA = lockA;
    this.lockB = lockB;
  }

  @Override
  public void run() {
    synchronized (lockA) {
      System.out.println(Thread.currentThread().getName() + "lock:" + lockA + " =>get" + lockB);

      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized (lockB) {
        System.out.println(Thread.currentThread().getName() + "lock:" + lockB + " =>get" + lockA);
      }
    }
  }
}
