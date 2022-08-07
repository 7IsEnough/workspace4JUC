package com.clearlove.single;

/**
 * @author promise
 * @date 2022/8/7 - 19:08
 */
public class Holder {

  private Holder() {

  }

  public static Holder getInstance() {
    return InnerClass.HOLDER;
  }

  public static class InnerClass {
    private static final Holder HOLDER = new Holder();
  }

}
