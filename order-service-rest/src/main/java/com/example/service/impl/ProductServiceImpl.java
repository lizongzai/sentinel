package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.pojo.Product;
import com.example.service.IProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-23
 */
@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private LoadBalancerClient loadBalancerClient; //Ribbon负载均衡器

//  /**
//   * 功能描述: 查询商品列表
//   *
//   * @return
//   */
//  // 声明需要服务容错的方法
//  // 线程池隔离
//  @HystrixCommand(groupKey = "order-productService-listPool",// 服务名称，相同名称使用同一个线程池
//      commandKey = "selectProductList",// 接口名称，默认为方法名
//      threadPoolKey = "order-productService-listPool",// 线程池名称，相同名称使用同一个线程池
//      commandProperties = {
//          // 超时时间，默认 1000ms
//          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//      },
//      threadPoolProperties = {
//          // 线程池大小
//          @HystrixProperty(name = "coreSize", value = "6"),
//          // 队列等待阈值(最大队列长度，默认 -1)
//          @HystrixProperty(name = "maxQueueSize", value = "100"),
//          // 线程存活时间，默认 1min
//          @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
//          // 超出队列等待阈值执行拒绝策略
//          @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100")
//      }, fallbackMethod = "selectProductListFallback") // 托底数据
//  @Cacheable(cacheNames = "orderService:product:list")
//  @Override
//  public List<Product> selectProductList() {
//    System.out.println(
//        Thread.currentThread().getName() + "-----orderService-----selectProductList-----");
//    return restTemplate.exchange("http://product-service/product/list",
//        HttpMethod.GET,
//        null,
//        new ParameterizedTypeReference<List<Product>>() {
//        }).getBody();
//  }
//

  /**
   * 功能描述: 查询商品列表
   *
   * @return
   */
// 声明需要服务容错的方法
  // 信号量隔离
  @HystrixCommand(commandProperties = {
      // 超时时间，默认 1000ms
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
          value = "5000"),
      // 信号量隔离
      @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY,
          value = "SEMAPHORE"),
      // 信号量最大并发，调小一些方便模拟高并发
      @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS,
          value = "6")
  }, fallbackMethod = "selectProductListFallback")
  @Cacheable(cacheNames = "orderService:product:list")
  @Override
  public List<Product> selectProductList() {
    System.out.println(
        Thread.currentThread().getName() + "-----orderService-----selectProductList-----");
    return restTemplate.exchange("http://product-service/product/list",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Product>>() {
        }).getBody();
  }


  /**
   * 功能描述: 根据多个主键查询商品
   *
   * @param ids
   * @return
   */
  @Cacheable(cacheNames = "orderService:product:listByIds")
  @Override
  public List<Product> selectProductListByIds(List<Integer> ids) {
    System.out.println(
        Thread.currentThread().getName() + "-----orderService-----selectProductListByIds-----");
    StringBuffer sb = new StringBuffer();
    ids.forEach(id -> sb.append("id=" + id + "&"));
    return restTemplate.getForObject("http://product-service/product/listByIds?" + sb.toString(),
        List.class);
  }

//  /**
//   * 功能描述: 根据主键查询商品
//   *
//   * @param id
//   * @return
//   */
//  // 声明需要服务容错的方法
//  // 线程池隔离
//  @HystrixCommand(groupKey = "order-productService-singlePool",// 服务名称，相同名称使用同一个线程池
//      commandKey = "selectProductById",// 接口名称，默认为方法名
//      threadPoolKey = "order-productService-singlePool",// 线程池名称，相同名称使用同一个线程池
//      commandProperties = {
//          // 超时时间，默认 1000ms
//          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//              value = "5000")
//      },
//      threadPoolProperties = {
//          // 线程池大小
//          @HystrixProperty(name = "coreSize", value = "3"),
//          // 队列等待阈值(最大队列长度，默认 -1)
//          @HystrixProperty(name = "maxQueueSize", value = "100"),
//          // 线程存活时间，默认 1min
//          @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
//          // 超出队列等待阈值执行拒绝策略
//          @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100")
//      })
//  @Cacheable(cacheNames = "orderService:product:single", key = "#id")
//  @Override
//  public Product selectProductById(Integer id) {
//    System.out.println(
//        Thread.currentThread().getName() + "-----orderService-----selectProductById-----");
//    return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
//  }

//  /**
//   * 功能描述: 根据主键查询商品
//   *
//   * @param id
//   * @return
//   */
//  // 声明需要服务容错的方法
//  // 服务熔断
//  @HystrixCommand(commandProperties = {
//      // 当请求符合熔断条件触发 fallbackMethod 默认 20 个
//      @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),
//      // 请求错误率大于 50% 就启动熔断器，然后 for 循环发起重试请求，当请求符合熔断条件触发 fallbackMethod
//      @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
//      // 熔断多少秒后去重试请求，默认 5s
//      @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
//  }, fallbackMethod = "selectProductByIdFallback")
//  @Override
//  public Product selectProductById(Integer id) {
//    //System.out.println(Thread.currentThread().getName() + "-----orderService-----selectProductById-----");
//    System.out.println("-----selectProductById-----" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
//
//    // 模拟查询主键为 1 的商品信息会导致异常
//    if (1 == id) {
//      throw new RuntimeException("查询主键为 1 的商品信息导致异常");
//    }
//
//    return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
//  }


  /**
   * 功能描述: 根据主键查询商品
   *
   * @param id
   * @return
   */
  //Hystrix声明需要服务容错的方法
  //服务降级
  //@HystrixCommand(fallbackMethod = "selectProductByIdFallback")

  //阿里巴巴Sentinel服务哨兵容错的方法
  //@SentinelResource(value = "selectProductById", blockHandler = "selectProductByIdBlockHandler", fallback = "selectProductByIdFallback")
  @Override
  public Product selectProductById(Integer id) {
    System.out.println(Thread.currentThread().getName() + "-----orderService-----selectProductById-----");
    //System.out.println("-----selectProductById-----" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

    // 验证降级方法一: 模拟查询主键为 1 的商品信息会导致异常
//        if (1 == id) {
//          throw new RuntimeException("查询主键为 1 的商品信息导致异常");
//        }

    // 验证降级方法二: 不同类型的数据转换
    //    Integer.parseInt("T");

    // 验证降级方法三: 将product-service项目停止服务

    return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
  }


  // 托底数据
//  private List<Product> selectProductListFallback() {
//    System.out.println("-----selectProductListFallback-----");
//    return Arrays.asList(
//        new Product(1, "托底数据-华为手机", 1, 5800D),
//        new Product(2, "托底数据-联想笔记本", 1, 6888D),
//        new Product(3, "托底数据-小米平板", 5, 2020D)
//    );
//  }

  // 托底数据
//  private Product selectProductByIdFallback(Integer id) {
//    return new Product(id, "托底数据", 1, 9999D);
//  }

  // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
  public Product selectProductByIdBlockHandler(Integer id, BlockException ex) {
    // Do some log here.
    ex.printStackTrace();
    return new Product(id, "服务流量控制处理-托底数据", 1, 2666D);
  }

  // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
  public Product selectProductByIdFallback(Integer id, Throwable throwable) {
    System.out.println("product-service 服务的 selectProductById 方法出现异常，异常信息如下: " + throwable);
    return new Product(id, "服务熔断降级处理-托底数据", 1, 2666D);
  }

}
