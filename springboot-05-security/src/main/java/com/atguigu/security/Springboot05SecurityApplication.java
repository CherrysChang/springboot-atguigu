package com.atguigu.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 市面上2个比较常用的安全框架：Apache的Shiro（强大易用） 和 Spring Security（复杂，功能强大，可以无缝整合Spring。SpringBoot底层采用的就是这个作为安全框架）
 * 
 * 1、引入SpringSecurity；（引入starter-security）
 * 2、编写SpringSecurity的配置类；（可以参见Spring官网查看如何配置。这里自定义的配置类参见MySecurityConfig ）
 * 	标注@EnableWebSecurity注解，并（继承）extends WebSecurityConfigurerAdapter
 * 3、控制请求的访问权限：
 * 		configure(HttpSecurity http) {
 * 		 	http.authorizeRequests().antMatchers("/").permitAll()
 * 		 		.antMatchers("/level1/**").hasRole("VIP1")
 * 		}
 * 4、定义认证规则：
 * 		configure(AuthenticationManagerBuilder auth){
 * 		 	auth.inMemoryAuthentication()
 * 		 		.withUser("zhangsan").password("123456").roles("VIP1","VIP2")
 * 		}
 * 5、开启自动配置的登陆功能：
 * 		configure(HttpSecurity http){
 * 		 	http.formLogin();
 * 		}
 * 6、注销：http.logout();
 * 7、记住我：Remeberme()；
 */
@SpringBootApplication
public class Springboot05SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot05SecurityApplication.class, args);
	}
}
