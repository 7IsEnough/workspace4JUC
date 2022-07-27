package com.clearlove.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author promise
 * @date 2022/7/27 - 23:11
 */
public class CallableTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
//    new Thread(new Runnable()).start();
//    new Thread(new FutureTask<V>()).start();
//    new Thread(new FutureTask<V>(Callable)).start();

    // 怎么启动Callable
    MyThread thread = new MyThread();
    // 适配类
    FutureTask<Integer> futureTask = new FutureTask<>(thread);
    new Thread(futureTask, "A").start();
    // 结果会被缓存，效率高
    new Thread(futureTask, "B").start();

    // 获取Callable的返回结果
    // 这个get方法可能会产生阻塞 一般把它放到最后
    // 或者使用异步通信来处理
    System.out.println(futureTask.get());
  }
}

class MyThread implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println("call()");
    return 1024;
  }
}
