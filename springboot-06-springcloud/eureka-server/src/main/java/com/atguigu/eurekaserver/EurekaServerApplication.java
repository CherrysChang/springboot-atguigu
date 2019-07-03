package com.atguigu.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * （idea创建项目勾选cloud discovery下的eureka-server，服务提供者和消费者都是在注册中心里，它们创建勾选的是eureka-discovery）
 * 1、配置Eureka信息（在application.properties或yml配置）
 * 2、@EnableEurekaServer（启用注册中心）
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
