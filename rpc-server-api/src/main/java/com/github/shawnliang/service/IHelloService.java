package com.github.shawnliang.service;

import com.github.shawnliang.dto.UserDto;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public interface IHelloService {

  /**
   * 打招呼
   * @param content 打招呼的内容
   * @return
   */
  String sayHello(String content);

  /**
   * 保存用户
   * @param userDto 用户Dto
   * @return
   */
  String saveUser(UserDto userDto);

}
