package com.github.shawnliang;

import com.github.shawnliang.proxy.RpcProxyServer;
import com.github.shawnliang.service.impl.IHelloServiceImpl;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class App {

  public static void main(String[] args) {
    IHelloServiceImpl helloService = new IHelloServiceImpl();
    RpcProxyServer rpcProxyServer = new RpcProxyServer();
    rpcProxyServer.publish(8080, helloService);
  }

}
