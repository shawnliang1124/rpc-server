package com.github.shawnliang.process;

import com.github.shawnliang.request.RpcRequest;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class ProcessorHandler implements Runnable{

  private Socket socket;

  private Object service;

  public ProcessorHandler(Socket socket, Object service) {
    this.socket = socket;
    this.service = service;
  }

  @Override
  public void run() {
    try(ObjectInputStream objectInputStream =  new ObjectInputStream(socket.getInputStream());ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
      RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
      Object result = invoke(rpcRequest);
      objectOutputStream.writeObject(result);
      objectOutputStream.flush();
    } catch (Exception e)  {
        e.printStackTrace();
    }
  }

  /**
   * 反射调用执行方法
   * @param rpcRequest rpc执行的结果
   * @return
   */
  private Object invoke(RpcRequest rpcRequest) throws Exception {
    Object[] params = rpcRequest.getParams();
    Class<?>[] types = new Class[params.length];
    for (int i = 0; i < params.length; i++) {
      types[i] = params[i].getClass();
    }
    Class<?> clazz = Class.forName(rpcRequest.getClassName());
    Method method = clazz.getMethod(rpcRequest.getMethodName(), types);
    return method.invoke(service, params);
  }
}
