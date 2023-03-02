package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.example.mapper")
public class OrderServiceFeignApplication {

//  @Bean
//  public Logger.Level getLog() {
//    return Level.FULL;
//  }
//
//  @Bean
//  public RandomRule randomRule() {
//    return new RandomRule();
//  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceFeignApplication.class, args);
  }
}
