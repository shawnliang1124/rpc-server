package com.github.shawnliang.process;

import com.github.shawnliang.request.RpcRequest;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;
import org.springframework.util.StringUtils;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class ProcessorHandler implements Runnable{

  private Socket socket;

  private Map<String,Object> map;

  public ProcessorHandler(Socket socket, Map<String,Object> map) {
    this.socket = socket;
    this.map = map;
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
    // 反射调用
    String fullName;
    String className = rpcRequest.getClassName();
    fullName = className;
    String version = rpcRequest.getVersion();
    if (!StringUtils.isEmpty(version)) {
      fullName += "-" + version;
    }
    Object serviceBean = map.get(fullName);
    if (serviceBean == null) {
       throw new RuntimeException("service not found:"+ fullName);
    }

    Object[] params = rpcRequest.getParams();
    Class<?>[] types = new Class[params.length];
    for (int i = 0; i < params.length; i++) {
      types[i] = params[i].getClass();
    }
    Class<?> clazz = Class.forName(className);
    Method method = clazz.getMethod(rpcRequest.getMethodName(), types);
    return method.invoke(serviceBean, params);
  }
}
