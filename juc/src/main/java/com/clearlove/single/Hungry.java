package com.clearlove.single;

/**
 * @author promise
 * @date 2022/8/7 - 17:55
 * 饿汉式单例
 */
public class Hungry {

  // 可能会浪费空间
  private byte[] data1 = new byte[1024*1024];
  private byte[] data2 = new byte[1024*1024];
  private byte[] data3 = new byte[1024*1024];
  private byte[] data4 = new byte[1024*1024];

  private Hungry() {

  }

  private final static Hungry HUNGRY = new Hungry();


  public static Hungry getInstance() {
    return HUNGRY;
  }

}
