package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Order;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-24
 */
public interface OrderMapper extends BaseMapper<Order> {

  /**
   * 获取订单
   *
   * @param id
   * @return
   */
  Order getOrderById(Integer id);

  /**
   * 功能描述: 根据主键查询订单
   *
   * @param id
   * @return
   */
  Order searchOrderById(Integer id);
}
