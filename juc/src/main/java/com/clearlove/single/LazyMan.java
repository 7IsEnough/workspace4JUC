package com.clearlove.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author promise
 * @date 2022/8/7 - 17:58
 * 懒汉式单例模式
 */
public class LazyMan {

  private static boolean clearlove = false;

  private LazyMan() {
    synchronized (LazyMan.class) {
      if (!clearlove) {
        clearlove = true;
      } else {
        throw new RuntimeException("不要试图使用反射破坏异常");
      }
    }
    System.out.println(Thread.currentThread().getName() + " ok");
  }

  private volatile static LazyMan lazyMan;

  // 单线程下确实OK
  // 双重检测锁模式下的 懒汉式单例  DCL懒汉式
  public static LazyMan getInstance() {
    if (lazyMan == null) {
      synchronized (LazyMan.class) {
        if (lazyMan == null) {
          // 不是一个原子性操作
          /**
           * 1.分配内存空间
           * 2.执行构造方法，初始化对象
           * 3.把这个对象指向这个空间
           *
           * A线程按132顺序运行，B线程在第一个非空判断会跳过，但此时的lazyMan还没有初始化完成
           */
          lazyMan = new LazyMan();
        }
      }
    }
    return lazyMan;
  }

  // 多线程并发
  public static void main(String[] args) throws Exception {
//    LazyMan instance = LazyMan.getInstance();
    Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
    declaredConstructor.setAccessible(true);

    Field clearlove = LazyMan.class.getDeclaredField("clearlove");
    clearlove.setAccessible(true);

    LazyMan instance = declaredConstructor.newInstance();
    clearlove.set(instance, false);
    LazyMan instance2 = declaredConstructor.newInstance();
    System.out.println(instance);
    System.out.println(instance2);
  }
}
