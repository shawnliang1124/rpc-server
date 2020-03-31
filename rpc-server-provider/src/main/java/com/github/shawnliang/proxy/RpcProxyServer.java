package com.github.shawnliang.proxy;

import com.github.shawnliang.process.ProcessorHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class RpcProxyServer {

  ExecutorService executorService = Executors.newCachedThreadPool();

  public void publish(int port, Object service) {
    ServerSocket serverSocket = null;

    try {
      serverSocket = new ServerSocket(port);
      // 死循环用于不断地接受请求
      while (true) {
        Socket socket = serverSocket.accept();
        // 每一个socket 都交给processorHandler去处理
        executorService.execute(new ProcessorHandler(socket, service));
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





}
