package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ProductMapper;
import com.example.pojo.Product;
import com.example.pojo.RespBean;
import com.example.service.IProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-23
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements
    IProductService {

  @Autowired
  @Resource
  private ProductMapper productMapper;

  /**
   * 功能描述: 查询商品列表
   *
   * @return
   */
  @Override
  public List<Product> selectProductList() {

    //    return Arrays.asList(
    //        new Product(1, "华为手机", 1, 5800D),
    //        new Product(2, "联想电脑", 1, 29999D),
    //        new Product(3, "苹果手机", 1, 9899D)
    //    );
    return productMapper.selectProductList();
  }

  /**
   * 功能描述: 根据多个主键查询商品
   *
   * @param ids
   * @return
   */
  @Override
  public List<Product> selectProductListByIds(List<Integer> ids) {
    return productMapper.selectProductListByIds(ids);
  }

  /**
   * 功能描述: 根据主键查询商品
   *
   * @param id
   * @return
   */
  @Override
  public Product selectProductById(Integer id) {
    return productMapper.selectProductById(id);
  }

  /**
   * 功能描述: 使用POST方法,根据主键查询商品
   *
   * @param id
   * @return
   */
  @Override
  public Product queryProductById(Integer id) {
    return productMapper.queryProductById(id);
  }

  /**
   * 功能描述: 使用POST方法,添加商品
   *
   * @param product
   * @return
   */
  @Override
  public RespBean addProduct(Product product) {

    int result = productMapper.addProduct(product);
    System.out.println("添加商品 = " + product);
    if (result > 0) {
      return RespBean.success("添加成功!");
    } else {
      return RespBean.success("添加失败!");
    }
  }

  /**
   * 功能描述: 接收商品对象参数
   *
   * @param product
   * @return
   */
  @Override
  public Product selectProductByPojo(Product product) {
    System.out.println("接收商品对象参数 = " + product);
    return product;
  }
}
