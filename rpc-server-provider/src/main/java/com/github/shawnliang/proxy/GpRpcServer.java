package com.github.shawnliang.proxy;

import com.github.shawnliang.annonation.RpcService;
import com.github.shawnliang.process.ProcessorHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Description : 将socket对象放到 GpRpcServer中，让spring容器进行管理
 *
 * @author : Phoebe
 * @date : Created in 2020/4/4
 */
public class GpRpcServer implements ApplicationContextAware, InitializingBean {
  ExecutorService executorService = Executors.newCachedThreadPool();
  /**
   * key 是 serviceName value是serviceBean对象
   */
  private Map<String,Object> map = new ConcurrentHashMap<>(32);
  private int port;

  public GpRpcServer(int port) {
    this.port = port;
  }

  @Override
  public void afterPropertiesSet() throws Exception {


    ServerSocket serverSocket = null;

    try {
      serverSocket = new ServerSocket(port);
      // 死循环用于不断地接受请求
      while (true) {
        Socket socket = serverSocket.accept();
        // 每一个socket 都交给processorHandler去处理
        executorService.execute(new ProcessorHandler(socket, map));
      }


    } catch (Exception e) {

    }finally {
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    Map<String, Object> serviceBeanMap = applicationContext
        .getBeansWithAnnotation(RpcService.class);
    if (!serviceBeanMap.isEmpty()) {
      for (Object serviceBean : serviceBeanMap.values()) {
        // 拿到对应的注解
        RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
        // 拿到注解上的name
        String serviceName = rpcService.value().getName();
        String version = rpcService.version();
        if (!StringUtils.isEmpty(version)) {
            // 加上拼接的参数
            serviceName += "-" + version;
        }
        map.put(serviceName,serviceBean);
      }
      ;
    }

  }
}
