package com.clearlove.blockQueen;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author promise
 * @date 2022/7/31 - 17:51
 */
public class Test {

  // Collection
  // List
  // Set
  // Queue -> BlockingQueue

  public static void main(String[] args) throws InterruptedException {
    //    test1();
    //    test2();
    //    test3();
    test4();
  }

  // 抛出异常
  public static void test1() {
    // 参数：队列的大小
    ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
    System.out.println(blockingQueue.add("a"));
    System.out.println(blockingQueue.add("b"));
    System.out.println(blockingQueue.add("c"));

    // 检测队首的元素
    System.out.println(blockingQueue.element());
    System.out.println("==============");

    // 抛出异常  IllegalStateException: Queue full
    //    System.out.println(blockingQueue.add("d"));

    System.out.println(blockingQueue.remove());
    System.out.println(blockingQueue.remove());
    System.out.println(blockingQueue.remove());

    // 抛出异常 java.util.NoSuchElementException
    //    System.out.println(blockingQueue.remove());
  }

  // 有返回值，没有异常
  public static void test2() {
    // 参数：队列的大小
    ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    System.out.println(blockingQueue.offer("a"));
    System.out.println(blockingQueue.offer("b"));
    System.out.println(blockingQueue.offer("c"));

    // 返回false 不抛出异常
    //    System.out.println(blockingQueue.offer("d"));

    // 检测队首的元素
    System.out.println(blockingQueue.peek());
    System.out.println("================");
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());

    // null 不抛出异常
    //    System.out.println(blockingQueue.poll());
  }

  // 等待 阻塞 (一直阻塞)
  public static void test3() throws InterruptedException {
    // 参数：队列的大小
    ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    // 一直阻塞
    blockingQueue.put("a");
    blockingQueue.put("b");
    blockingQueue.put("c");

    // 队列没有位置了，一直阻塞
    //    blockingQueue.put("d");

    System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take());

    // 没有元素可取，一直阻塞
    //    System.out.println(blockingQueue.take());
  }

  // 等待 阻塞 (等待超时)
  public static void test4() throws InterruptedException {
    ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    blockingQueue.offer("a");
    blockingQueue.offer("b");
    blockingQueue.offer("c");

    // 等待超过2s就退出
    //    blockingQueue.offer("d", 2, TimeUnit.SECONDS);

    System.out.println("================");

    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    // 等待超过2s就退出
    blockingQueue.poll(2, TimeUnit.SECONDS);
  }
}
