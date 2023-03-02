package com.example.service;

import com.example.pojo.Product;
import java.util.List;

/**
 * 功能描述: 调用服务生产者的微服务名称,例如：service-provider
 *
 * @author lizongzai
 * @date 2023/02/27 11:50
 * @since 1.0.0
 */
public interface IProductService {

  /**
   * 功能描述: 查询商品列表
   *
   * @return
   */
  //配置需要调用的微服务地址和参数
  List<Product> selectProductList();

  /**
   * 功能描述: 根据主键查询商品
   *
   * @param ids
   * @return
   */
  List<Product> selectProductListByIds(List<Integer> ids);


  /**
   * 功能描述: 使用POST方法,根据主键查询商品
   *
   * @param id
   * @return
   */
  Product selectProductById(Integer id);


}
