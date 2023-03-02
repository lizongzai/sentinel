package com.example.service;

import com.example.fallback.ProductServiceFallbackFactory;
import com.example.pojo.Product;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述: 调用服务生产者的微服务名称,例如：product-service
 *
 * @author lizongzai
 * @date 2023/03/01 14:09
 * @since 1.0.0
 */
// 声明需要调用的服务和服务熔断处理类
@FeignClient(name = "product-service", value = "product-service", fallbackFactory = ProductServiceFallbackFactory.class)
public interface IProductService {

  /**
   * 查询商品列表
   *
   * @return
   */
  @GetMapping("/product/list")
  List<Product> selectProductList();

  /**
   * 根据多个主键查询商品
   *
   * @param ids
   * @return
   */
  @GetMapping("/product/listByIds")
  List<Product> selectProductListByIds(@RequestParam("id") List<Integer> ids);

  /**
   * 根据主键查询商品
   *
   * @param id
   * @return
   */
  @GetMapping("/product/{id}")
  Product selectProductById(@PathVariable("id") Integer id);

}
