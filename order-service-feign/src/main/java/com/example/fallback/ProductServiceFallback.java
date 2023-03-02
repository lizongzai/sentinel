package com.example.fallback;

import com.example.pojo.Product;
import com.example.service.IProductService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述: 服务熔断降级处理
 *
 * @author lizongzai
 * @date 2023/03/01 14:45
 * @since 1.0.0
 */
//@Component
public class ProductServiceFallback implements IProductService {

  /**
   * 功能描述: 查询商品列表接口的托底数据
   *
   * @return
   */
  @Override
  public List<Product> selectProductList() {
    return Arrays.asList(
        new Product(1, "托底数据-华为手机", 1, 5800D),
        new Product(2, "托底数据-联想笔记本", 1, 6888D),
        new Product(3, "托底数据-小米平板", 5, 2020D)
    );
  }

  /**
   * 根据多个主键查询商品接口的托底数据
   *
   * @param ids
   * @return
   */
  @Override
  public List<Product> selectProductListByIds(List<Integer> ids) {
    List<Product> products = new ArrayList<>();
    ids.forEach(id -> products.add(new Product(id, "托底数据-电视机" + id, 1, 5800D)));
    return products;
  }

  /**
   * 根据主键查询商品接口的托底数据
   *
   * @param id
   * @return
   */
  @Override
  public Product selectProductById(Integer id) {
    return new Product(id, "托底数据", 1, 9999D);
  }
}
