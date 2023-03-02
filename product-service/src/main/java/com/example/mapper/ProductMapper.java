package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Product;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-23
 */
public interface ProductMapper extends BaseMapper<Product> {

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
  List<Product> selectProductListByIds(@Param("ids") List<Integer> ids);

  /**
   * 功能描述: 根据主键查询商品
   *
   * @param id
   * @return
   */
  Product selectProductById(@Param("id") Integer id);

  /**
   * 功能描述: 使用POST方法,根据主键查询商品
   *
   * @param id
   * @return
   */
  Product queryProductById(@Param("id") Integer id);

  /**
   * 功能描述: 使用POST方法,添加商品
   *
   * @param product
   * @return
   */
  int addProduct(Product product);


}
