package com.github.shawnliang.request;

import java.io.Serializable;

/**
 * Description :   .
 *
 * @author : Phoebe
 * @date : Created in 2020/3/31
 */
public class RpcRequest implements Serializable {

  private String className;

  private String methodName;

  private Object[] params;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Object[] getParams() {
    return params;
  }

  public void setParams(Object[] params) {
    this.params = params;
  }
}
