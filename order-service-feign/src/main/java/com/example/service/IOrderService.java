package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Order;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lizongzai
 * @date 2023/03/01 14:09
 * @since 1.0.0
 */
public interface IOrderService extends IService<Order> {

  /**
   * 根据主键查询订单-->调用商品服务/product/list
   *
   * @param id
   * @return
   */
  Order selectOrderById(Integer id);

  /**
   * 根据主键查询订单-->调用商品服务/product/listByIds
   *
   * @param id
   * @return
   */
  Order queryOrderById(Integer id);

  /**
   * 根据主键查询订单-->调用商品服务/product/{id}
   *
   * @param id
   * @return
   */
  Order searchOrderById(Integer id);
}
