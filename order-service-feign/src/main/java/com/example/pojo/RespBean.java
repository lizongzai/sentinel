package com.example.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述: 公共返回结果
 *
 * @author lizongzai
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RespBean对象", description = "")
public class RespBean {

  private long code;
  private String message;
  private Object object;

  /**
   * 功能描述:返回成功结果
   *
   * @param message
   * @return
   */
  public static RespBean success(String message) {
    return new RespBean(200, message, null);
  }

  /**
   * 功能描述:返回成功结果
   *
   * @param message
   * @param object
   * @return
   */
  public static RespBean success(String message, Object object) {
    return new RespBean(200, message, object);
  }


  /**
   * 功能描述:失败成功结果
   *
   * @param message
   * @return
   */
  public static RespBean error(String message) {
    return new RespBean(500, message, null);
  }

  /**
   * 功能描述:失败成功结果
   *
   * @param message
   * @param object
   * @return
   */
  public static RespBean error(String message, Object object) {
    return new RespBean(500, message, object);
  }
}
