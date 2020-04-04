package com.github.shawnliang.service.impl;

import com.github.shawnliang.annonation.RpcService;
import com.github.shawnliang.dto.UserDto;
import com.github.shawnliang.service.IHelloService;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
@RpcService(value = IHelloService.class, version = "v1.0.0")
public class IHelloServiceImpl implements IHelloService {

  @Override
  public String sayHello(String content) {
    System.out.println("say hello "+ content + "v1.0.0");
    return "say hello "+ content + "v1.0.0";
  }

  @Override
  public String saveUser(UserDto userDto) {
    System.out.println("saving userDto "+ userDto.toString());
    return "saving userDto "+ userDto.toString();
  }
}
