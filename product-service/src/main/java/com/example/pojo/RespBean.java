package com.example.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="RespBean对象", description="")
public class RespBean {

  private long code;
  private String message;
  private Object obj;

  /**
   * @Description //TODO 成功返回结果
   * @Author lizongzai
   * @param message
   * @return
   */
  public static RespBean success(String message) {
    return new RespBean(200, message, null);
  }

  /**
   * @Description //TODO 成功返回结果
   * @Author lizongzai
   * @param message
   * @param obj
   * @return
   */
  public static RespBean success(String message,Object obj) {
    return new RespBean(200, message, obj);
  }

  /**
   * @Description //TODO 失败返回结果
   * @Author lizongzai
   * @param message
   * @return
   */
  public static RespBean error(String message) {
    return new RespBean(500, message, null);
  }

  /**
   * @Description //TODO 失败返回结果
   * @Author lizongzai
   * @param message
   * @param obj
   * @return
   */
  public static RespBean error(String message,Object obj) {
    return new RespBean(500, message, obj);
  }
}

