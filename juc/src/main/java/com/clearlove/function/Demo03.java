package com.clearlove.function;

import java.util.function.Consumer;

/**
 * @author promise
 * @date 2022/8/5 - 0:03
 * Consumer 消费型接口：只有输入，没有返回值
 */
public class Demo03 {

  public static void main(String[] args) {
//    Consumer<String> consumer = new Consumer<String>() {
//      @Override
//      public void accept(String str) {
//        System.out.println(str);
//      }
//    };

//    Consumer<String> consumer = str -> System.out.println(str);

    Consumer<String> consumer = System.out::println;

    consumer.accept("clearlove");
  }
}
