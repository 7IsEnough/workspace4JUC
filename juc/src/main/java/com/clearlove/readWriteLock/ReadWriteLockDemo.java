package com.clearlove.readWriteLock;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author promise
 * @date 2022/7/31 - 11:11
 * ReadWriteLock
 * 读-读  可以共存
 * 读-写  不能共存
 * 写-写  不能共存
 * 独占锁(写锁)  一次只能被一个线程占有
 * 共享锁(读锁)  多个线程可以同时占有
 */
public class ReadWriteLockDemo {

  public static void main(String[] args) {
    MyCacheLock myCache = new MyCacheLock();

    // 写入
    for (int i = 1; i <= 5; i++) {
      final int temp = i;
      new Thread(() -> {
        myCache.put(temp + "", temp + "");
      }, String.valueOf(i)).start();
    }

    // 读取
    for (int i = 1; i <= 5; i++) {
      final int temp = i;
      new Thread(() -> {
        myCache.get(temp + "");
      }, String.valueOf(i)).start();
    }
  }
}

/** 自定义缓存 */
class MyCache {

  private volatile Map<String, Object> map = Maps.newHashMap();

  // 存，写
  public void put(String key, Object value) {
    System.out.println(Thread.currentThread().getName() + "写入" + key);
    map.put(key, value);
    System.out.println(Thread.currentThread().getName() + "写入OK");
  }

  // 取，读
  public void get(String key) {
    System.out.println(Thread.currentThread().getName() + "读取" + key);
    map.get(key);
    System.out.println(Thread.currentThread().getName() + "读取OK");
  }
}

/** 加锁的 */
class MyCacheLock {

  private volatile Map<String, Object> map = Maps.newHashMap();
  // 读写锁  更加细粒度的控制
  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  // 存，写  写入的时候，只希望同时只有一个线程写
  public void put(String key, Object value) {
    readWriteLock.writeLock().lock();

    try {
      System.out.println(Thread.currentThread().getName() + "写入" + key);
      map.put(key, value);
      System.out.println(Thread.currentThread().getName() + "写入OK");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      readWriteLock.writeLock().unlock();
    }
  }

  // 取，读，所有人都可以读
  public void get(String key) {
    readWriteLock.readLock().lock();
    try {
      System.out.println(Thread.currentThread().getName() + "读取" + key);
      map.get(key);
      System.out.println(Thread.currentThread().getName() + "读取OK");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      readWriteLock.readLock().unlock();
    }
  }
}
