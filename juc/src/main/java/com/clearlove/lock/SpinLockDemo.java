package com.clearlove.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author promise
 * @date 2022/8/8 - 22:00
 * 自旋锁
 */
public class SpinLockDemo {

  // 默认值
  // int 0   Thread null
  AtomicReference<Thread> atomicReference = new AtomicReference<>();


  // 加锁
  public void myLock() {
    Thread thread = Thread.currentThread();
    System.out.println(Thread.currentThread().getName() + " => mylock");

    // 自旋锁
    while (!atomicReference.compareAndSet(null, thread)) {

    }
  }

  // 解锁
  public void myUnlock() {
    Thread thread = Thread.currentThread();
    System.out.println(Thread.currentThread().getName() + " => myUnlock");

    atomicReference.compareAndSet(thread,null);
  }



}
