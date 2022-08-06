package com.clearlove.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author promise
 * @date 2022/8/6 - 10:03
 */
@Data   // get set toString方法
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 全参构造方法
@Accessors(chain = true)
public class User {

  private int id;
  private String name;
  private int age;

}
