package com.atguigu.consumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //开启发现服务功能。（这样就可以从Eureka服务注册中心获取到其他服务，并能调用）
@SpringBootApplication
public class ConsumerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerUserApplication.class, args);
	}

	@LoadBalanced //使用负载均衡机制 （比如我们启动了服务提供者应用的多个实例，访问哪个服务采用轮询的方式）
	@Bean
	public RestTemplate restTemplate(){//注册restTemplate，帮我们发送http请求
		return new RestTemplate();
	}
}
