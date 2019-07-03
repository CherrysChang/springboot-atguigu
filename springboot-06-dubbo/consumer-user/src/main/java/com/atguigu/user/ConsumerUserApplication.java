package com.atguigu.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 2、将服务消费者 注册到服务注册中心 
 * 	1、引入dubbo和zkclient相关依赖
 * 	2、配置dubbo的注册中心地址
 * 	3、引用服务
 */
@SpringBootApplication
public class ConsumerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerUserApplication.class, args);
	}
}
