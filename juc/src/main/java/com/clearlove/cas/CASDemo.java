package com.clearlove.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author promise
 * @date 2022/8/7 - 22:09
 */
public class CASDemo {


  // CAS Compare-and-Swap
  public static void main(String[] args) {
//    AtomicInteger atomicInteger = new AtomicInteger(2020);

    // 注意：如果泛型是一个包装类，注意对象的引用问题
    // 正常在业务操作，这里比较的都是一个个对象
    AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(1, 1);

    // 对于我们平时写的SQL：乐观锁

    // public final boolean compareAndSet(int expectedValue, int newValue)
    // 参数含义：期望值  更新值
    // 如果我期望的值达到了，那么就更新，否则，就不更新
    // CAS 是CPU的并发原语
    //    // 捣乱的线程
    //    System.out.println(atomicInteger.compareAndSet(2020, 2021));
    //    System.out.println(atomicInteger.get());
    //
    //    System.out.println(atomicInteger.compareAndSet(2021, 2020));
    //    System.out.println(atomicInteger.get());
    //
    //    // 期望的线程
    //    System.out.println(atomicInteger.compareAndSet(2020, 6666));
    //    System.out.println(atomicInteger.get());

    new Thread(
            () -> {
              // 获得版本号
              int stamp = atomicStampedReference.getStamp();
              System.out.println("a1=>" + stamp);

              try {
                TimeUnit.SECONDS.sleep(2);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              System.out.println(
                  atomicStampedReference.compareAndSet(
                      1,
                      2,
                      atomicStampedReference.getStamp(),
                      atomicStampedReference.getStamp() + 1));

              System.out.println("a2=>" + atomicStampedReference.getStamp());

              System.out.println(
                  atomicStampedReference.compareAndSet(
                      2,
                      1,
                      atomicStampedReference.getStamp(),
                      atomicStampedReference.getStamp() + 1));

              System.out.println("a3=>" + atomicStampedReference.getStamp());
            },
            "a")
        .start();

    // 与乐观锁的原理相同
    new Thread(
            () -> {
              // 获得版本号
              int stamp = atomicStampedReference.getStamp();
              System.out.println("b1=>" + stamp);

              try {
                TimeUnit.SECONDS.sleep(2);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              System.out.println(
                  atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));

              System.out.println("b2=>" + atomicStampedReference.getStamp());
            },
            "b")
        .start();
  }
}
