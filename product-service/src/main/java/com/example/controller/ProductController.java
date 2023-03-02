package com.example.controller;


import com.example.pojo.Product;
import com.example.pojo.RespBean;
import com.example.service.IProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private IProductService productService;

  /**
   * 功能描述: 查询商品列表
   *
   * @return
   */
  @GetMapping("/list")
  public List<Product> selectProductList() {
    try {
      // 设置超时验证
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return productService.selectProductList();
  }

  /**
   * 功能描述: 根据多个主键查询商品
   *
   * @param ids
   * @return
   */
  @GetMapping("/listByIds")
  public List<Product> selectProductListByIds(@RequestParam("id") List<Integer> ids) {
    return productService.selectProductListByIds(ids);
  }

  /**
   * 功能描述: 使用GET方法, 根据主键查询商品
   *
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public Product selectProductById(@PathVariable("id") Integer id) {
//    try {
//      // 设置超时验证
//      Thread.sleep(2000L);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
    return productService.selectProductById(id);
  }

  /**
   * 功能描述: 使用POST方法,根据主键查询商品
   *
   * @param id
   * @return
   */
  @PostMapping("/single")
  public Product queryProductById(@RequestBody Integer id) {
    return productService.queryProductById(id);
  }


  /**
   * 功能描述: 使用POST方法,添加商品
   *
   * @param product
   * @return
   */
  @PostMapping("/save")
  public RespBean addProduct(@RequestBody Product product) {

    if (product.getProductName() == null || product.getProductNum() == null
        || product.getProductPrice() == null) {
      return RespBean.success("添加失败");
    }
    return productService.addProduct(product);
  }


  /**
   * 功能描述: 接收商品对象参数
   *
   * @param product
   * @return
   */
  @GetMapping("/pojo")
  public Product selectProductByPojo(@RequestBody Product product) {
    return productService.selectProductByPojo(product);
  }
}
