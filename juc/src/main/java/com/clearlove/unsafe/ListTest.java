package com.clearlove.unsafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author promise
 * @date 2022/7/26 - 22:58
 * java.util.ConcurrentModificationException 并发修改异常
 */
public class ListTest {

  public static void main(String[] args) {
    // 并发下 ArrayList 不安全的
    /**
     * 解决方案：
     * 1. List<String> list = new Vector<>();
     * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());
     * 3. List<String> list = new CopyOnWriteArrayList<>();
     */
    // CopyOnWrite 写入时赋值  COW 计算机程序设计领域的一种优化策略
    // 多个线程调用的时候，list，读取的时候是固定的，写入有可能会导致覆盖
    // 在写入的时候避免覆盖，造成数据问题
    // 读写分离
    List<String> list = new CopyOnWriteArrayList<>();
    for (int i = 1; i <= 10; i++) {
      new Thread(() -> {
        list.add(UUID.randomUUID().toString().substring(0,5));
        System.out.println(list);
      }, String.valueOf(i)).start();
    }
  }
}
