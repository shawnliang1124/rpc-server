package com.github.shawnliang.dto;

import java.io.Serializable;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class UserDto implements Serializable {

  /**
   * 姓名
   */
  private String name;

  /**
   * 名称
   */
  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "UserDto{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
