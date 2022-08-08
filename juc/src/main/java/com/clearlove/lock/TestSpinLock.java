package com.clearlove.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/8/8 - 22:07
 */
public class TestSpinLock {

  public static void main(String[] args) throws InterruptedException {

    // 底层使用自旋锁 CAS实现
    SpinLockDemo lock = new SpinLockDemo();

    new Thread(() -> {
      lock.myLock();

      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.myUnlock();
      }
    }, "T1").start();

    TimeUnit.SECONDS.sleep(1);


    new Thread(() -> {
      lock.myLock();

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.myUnlock();
      }
    }, "T2").start();

  }
}
