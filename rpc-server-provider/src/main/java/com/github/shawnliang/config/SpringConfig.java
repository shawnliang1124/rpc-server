package com.github.shawnliang.config;

import com.github.shawnliang.proxy.GpRpcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/4/4
 */
@Configuration
@ComponentScan("com.github.shawnliang")
public class SpringConfig {

  @Bean("gpRpcServer")
  public GpRpcServer gpRpcServer() {
    return new GpRpcServer(8080);
  }

}
