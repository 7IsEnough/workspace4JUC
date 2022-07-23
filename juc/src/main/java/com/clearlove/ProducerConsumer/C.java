package com.clearlove.ProducerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author promise
 * @date 2022/7/23 - 20:05
 */
public class C {

  public static void main(String[] args) {
    Data3 data = new Data3();

    new Thread(
            () -> {
              for (int i = 0; i < 10; i++) {
                data.printA();
              }
            },
            "A")
        .start();
    new Thread(()->{
      for (int i = 0; i < 10; i++) {
        data.printB();
      }
    }, "B").start();
    new Thread(()->{
      for (int i = 0; i < 10; i++) {
        data.printC();
      }
    }, "C").start();
  }
}

// 资源类，Lock
class Data3 {

  private Lock lock = new ReentrantLock();

  private Condition condition1 = lock.newCondition();
  private Condition condition2 = lock.newCondition();
  private Condition condition3 = lock.newCondition();

  private int number = 1;

  public void printA() {
    lock.lock();
    try {
      // 业务代码
      // 判断 -> 执行 -> 通知
      while (number != 1) {
        // 等待
        condition1.await();
      }
      System.out.println(Thread.currentThread().getName() + "=> AAAAAAA");
      // 精准唤醒B线程
      number = 2;
      condition2.signal();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

  }

  public void printB() {

    lock.lock();
    try {
      // 业务代码
      // 判断 -> 执行 -> 通知
      while (number != 2) {
        // 等待
        condition2.await();
      }
      System.out.println(Thread.currentThread().getName() + "=> BBBBBB");
      // 精准唤醒C线程
      number = 3;
      condition3.signal();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }


  }

  public void printC() {

    lock.lock();
    try {
      // 业务代码
      // 判断 -> 执行 -> 通知
      while (number != 3) {
        // 等待
        condition3.await();
      }
      System.out.println(Thread.currentThread().getName() + "=> CCCCCCC");
      // 精准唤醒A线程
      number = 1;
      condition1.signal();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }



  }

}
