package com.atguigu.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 一个 ElasticSearch 集群可以 包含多个 索引 ，相应的每个索引可以包含多个 类型 。 这些不同的类型存储着多个 文档 ，每个文档又有 多个 属性 。
 * 类似关系：
*	索引-数据库
*	类型-表
*	文档-表中的记录
*	属性-列
*
 * SpringBoot默认支持两种技术来和ES（elasticsearch）交互；
 * 1、Jest（默认不生效）
 * 	需要导入jest的工具包（io.searchbox.client.JestClient） 才会生效（JestAutoConfiguration自动配置类才会生效）。
 * 具体使用参见test目录下代码。
 * 2、SpringData ElasticSearch【ES版本有可能不合适】
 * 		版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *		如果版本不适配：2.4.6
 *			1）、升级SpringBoot版本
 *			2）、安装对应版本的ES
*	自动配置了如下信息：（ElasticsearchDataAutoCongiguration）		
 * 		1）、Client 节点信息clusterNodes；clusterName
 * 		2）、ElasticsearchTemplate 操作es
 *		3）、编写一个 ElasticsearchRepository 的子接口来操作ES；（类似于JPA的编程方式）
 *	两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *	1）、编写一个 ElasticsearchRepository子接口。比如BookRepository 。在test目录下进行测试
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03ElasticApplication.class, args);
	}
}
