package com.github.shawnliang;

import com.github.shawnliang.config.SpringConfig;
import com.github.shawnliang.proxy.RpcProxyServer;
import com.github.shawnliang.service.impl.IHelloServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class App {

  public static void main(String[] args) {
//    IHelloServiceImpl helloService = new IHelloServiceImpl();
////    RpcProxyServer rpcProxyServer = new RpcProxyServer();
////    rpcProxyServer.publish(8080, helloService);

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        SpringConfig.class);
    annotationConfigApplicationContext.start();
  }

}
