package com.atguigu.user;

import com.atguigu.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerUserApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {//测试消费者消费服务，前提服务者提供者一直运行中

		userService.hello();
	}

}
