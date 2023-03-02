package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Product;
import com.example.pojo.RespBean;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-23
 */
public interface IProductService extends IService<Product> {


  /**
   * 功能描述: 查询商品列表
   *
   * @return
   */
  List<Product> selectProductList();

  /**
   * 功能描述: 根据多个主键查询商品
   *
   * @param ids
   * @return
   */
  List<Product> selectProductListByIds(List<Integer> ids);

  /**
   * 功能描述: 使用GET方法,根据主键查询商品
   *
   * @return
   */
  Product selectProductById(Integer id);


  /**
   * 功能描述: 使用POST方法,根据主键查询商品
   *
   * @param id
   * @return
   */
  Product queryProductById(Integer id);


  /**
   * 功能描述: 使用POST方法,添加商品
   *
   * @param product
   * @return
   */
  RespBean addProduct(Product product);

  /**
   * 功能描述: 接收商品对象参数
   *
   * @param product
   * @return
   */
  Product selectProductByPojo(Product product);

}
