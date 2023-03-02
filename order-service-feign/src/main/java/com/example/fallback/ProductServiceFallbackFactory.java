package com.example.fallback;

import com.example.pojo.Product;
import com.example.service.IProductService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 服务熔断降级处理可以捕获异常
 *
 * @author lizongzai
 * @date 2023/03/01 14:45
 * @since 1.0.0
 */
@Component
@Slf4j
public class ProductServiceFallbackFactory implements FallbackFactory<IProductService> {

  @Override
  public IProductService create(Throwable throwable) {
    //通过匿名内部类返回结果
    return new IProductService() {

      /**
       * 功能描述: 查询商品列表接口的托底数据
       *
       * @return
       */
      @Override
      public List<Product> selectProductList() {
        log.error(
            "product-service 服务的 selectProductList 方法出现异常，异常信息如下：" + throwable);
        return Arrays.asList(new Product(1, "托底数据-华为手机", 1, 5800D),
            new Product(2, "托底数据-联想笔记本", 1, 6888D),
            new Product(3, "托底数据-小米平板", 5, 2020D));
      }

      /**
       * 功能描述: 根据多个主键查询商品接口的托底数据
       *
       * @param ids
       * @return
       */
      @Override
      public List<Product> selectProductListByIds(List<Integer> ids) {
        log.error(
            "product-service 服务的 selectProductListByIds 方法出现异常，异常信息如下：" + throwable);
        List<Product> products = new ArrayList<>();
        ids.forEach(id -> products.add(new Product(id, "托底数据-电视机" + id, 1, 5800D)));
        return products;
      }

      @Override
      public Product selectProductById(Integer id) {
        log.error(
            "product-service 服务的 selectProductById 方法出现异常，异常信息如下：" + throwable);
        return new Product(id, "托底数据", 1, 2666D);
      }
    };
  }
}