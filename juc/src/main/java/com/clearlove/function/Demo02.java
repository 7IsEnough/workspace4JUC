package com.clearlove.function;

import java.util.function.Predicate;

/**
 * @author promise
 * @date 2022/8/4 - 23:49
 * 断定型接口：有一个输入参数，返回值只能是布尔值
 */
public class Demo02 {

  public static void main(String[] args) {
  // 判断字符串是否为空
//    Predicate<String> predicate = new Predicate<String>() {
//      @Override
//      public boolean test(String str) {
//        return str.isEmpty();
//      }
//    };

//    Predicate<String> predicate = str -> str.isEmpty();

    Predicate<String> predicate = String::isEmpty;

    System.out.println(predicate.test("clearlove"));
    System.out.println(predicate.test(""));
  }
}
