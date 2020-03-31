package com.github.shawnliang.service.impl;

import com.github.shawnliang.dto.UserDto;
import com.github.shawnliang.service.IHelloService;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class IHelloServiceImpl implements IHelloService {

  public String sayHello(String content) {
    System.out.println("say hello "+ content);
    return "say hello "+ content;
  }

  public String saveUser(UserDto userDto) {
    System.out.println("saving userDto "+ userDto.toString());
    return "saving userDto "+ userDto.toString();
  }
}
