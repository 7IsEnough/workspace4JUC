package com.clearlove.function;

import java.util.function.Function;

/**
 * @author promise
 * @date 2022/8/4 - 23:39
 * Function 函数型接口
 * 有一个输入参数，有一个输出
 * 只要是函数式接口，就可以用lambda表达式简化
 */
public class Demo01 {

  public static void main(String[] args) {
    // 工具类：输出输入的值
//    Function<String, String> function = new Function<>() {
//      @Override
//      public String apply(String str) {
//        return str;
//      }
//    };

//   Function<String, String> function = (str) -> {
//      return str;
//    };

    Function<String, String> function = (str) -> str;

    System.out.println(function.apply("clearlove"));
  }
}
