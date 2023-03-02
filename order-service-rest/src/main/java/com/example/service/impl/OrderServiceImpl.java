package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.OrderMapper;
import com.example.pojo.Order;
import com.example.pojo.Product;
import com.example.service.IOrderService;
import com.example.service.IProductService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lizongzai
 * @date 2023/02/27 11:50
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

  @Autowired
  @Resource
  private OrderMapper orderMapper;
  @Autowired
  @Resource
  private RestTemplate restTemplate;
  @Autowired
  private IProductService productService;
  @Autowired
  private LoadBalancerClient loadBalancerClient; //Ribbon负载均衡器

  /**
   * 根据主键查询订单-->调用商品服务/product/list
   *
   * @param id
   * @return
   */
  @Override
  public Order selectOrderById(Integer id) {

    //查询商品列表
    List<Product> productList = productService.selectProductList();

    //System.out.println("商品信息 = " + productList);

    //获取订单信息
    Order mapperOrderById = orderMapper.getOrderById(id);
    Order order = new Order();
    order.setId(mapperOrderById.getId());
    order.setOrderNo(mapperOrderById.getOrderNo());
    order.setOrderAddress(mapperOrderById.getOrderAddress());
    order.setTotalPrice(mapperOrderById.getTotalPrice());
    order.setProductList(productList);
    return order;
  }

  /**
   * 根据主键查询订单-->调用商品服务/product/listByIds
   *
   * @param id
   * @return
   */
  @Override
  public Order queryOrderById(Integer id) {
    //查询商品列表
    List<Product> productList = productService.selectProductListByIds(Arrays.asList(1,2,3));

    //System.out.println("商品信息 = " + productList);

    //获取订单信息
    Order mapperOrderById = orderMapper.getOrderById(id);
    Order order = new Order();
    order.setId(mapperOrderById.getId());
    order.setOrderNo(mapperOrderById.getOrderNo());
    order.setOrderAddress(mapperOrderById.getOrderAddress());
    order.setTotalPrice(mapperOrderById.getTotalPrice());
    order.setProductList(productList);
    return order;
  }

  /**
   * 根据主键查询订单-->调用商品服务/product/{id}
   *
   * @param id
   * @return
   */
  @Override
  public Order searchOrderById(Integer id) {
    //查询商品列表
    Product productList = productService.selectProductById(id);

    //System.out.println("商品信息 = " + productList);

    //获取订单信息
    Order mapperOrderById = orderMapper.getOrderById(id);
    Order order = new Order();
    order.setId(mapperOrderById.getId());
    order.setOrderNo(mapperOrderById.getOrderNo());
    order.setOrderAddress(mapperOrderById.getOrderAddress());
    order.setTotalPrice(mapperOrderById.getTotalPrice());
    order.setProductList(Collections.singletonList(productList));
    return order;
  }

}
