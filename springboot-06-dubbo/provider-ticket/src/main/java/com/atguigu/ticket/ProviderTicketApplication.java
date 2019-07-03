package com.atguigu.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、将服务提供者注册到注册中心
 * 步骤：1、引入dubbo和zkclient相关依赖（这里引入dubbo-spring-boot-starter和zookeeper的客户端工具zkclient）
 * 	2、配置dubbo的扫描包和注册中心地址（在application.properties配置）
 * 	3、使用@Service发布服务（@Service这个注解是dubbo包下的）
 
 * ps：zookeeper作为服务注册中心，dubbo作为分布式服务调用框架（比如负责A模块和B模块远程过程调用）
 */
@SpringBootApplication
public class ProviderTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderTicketApplication.class, args);
	}
}
