package com.example;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.example.config.ExceptionUtil;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrixDashboard // 开启数据监控注解
@EnableHystrix  // 开启熔断器注解 2 选 1，@EnableHystrix 封装了 @EnableCircuitBreaker
@EnableTurbine // 开启数据监控注解
@EnableCaching // 开启缓存注解
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.example.mapper")
public class OrderServiceRestApplication {

  @Bean
  public RandomRule randomRule() {
    return new RandomRule();
  }

  @Bean
  @LoadBalanced
  @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class,
      fallback = "fallback", fallbackClass = ExceptionUtil.class)
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceRestApplication.class, args);
  }

}
