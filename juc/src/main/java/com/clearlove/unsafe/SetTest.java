package com.clearlove.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author promise
 * @date 2022/7/27 - 22:12
 * 同理可证 ConcurrentModificationException 并发修改异常
 */
public class SetTest {

  public static void main(String[] args) {
//    Set<String> set = new HashSet<>();
//    Set<String> set = Collections.synchronizedSet(new HashSet<>());
    Set<String> set = new CopyOnWriteArraySet<>();

    /**
     * 解决方案：
     * 1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
     * 2. Set<String> set = new CopyOnWriteArraySet<>();
     */

    for (int i = 1; i <= 30; i++) {
      new Thread(
              () -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
              },
              String.valueOf(i))
          .start();
    }
  }
}
