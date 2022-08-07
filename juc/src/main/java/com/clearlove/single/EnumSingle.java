package com.clearlove.single;

import java.lang.reflect.Constructor;

/**
 * @author promise
 * @date 2022/8/7 - 19:39
 * enum 本身也是一个class类
 */
public enum EnumSingle {

  INSTANCE;

  public EnumSingle getInstance() {
    return INSTANCE;
  }

}

class Test {
  public static void main(String[] args) throws Exception {
    EnumSingle instance1 = EnumSingle.INSTANCE;
    Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
    declaredConstructor.setAccessible(true);

    EnumSingle instance2 = declaredConstructor.newInstance();

    // java.lang.NoSuchMethodException: com.clearlove.single.EnumSingle.<init>()
    System.out.println(instance1);
    System.out.println(instance2);
  }
}
