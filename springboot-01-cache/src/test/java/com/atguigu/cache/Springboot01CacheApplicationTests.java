package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;

       //StringRedisTemplate、RedisTemplate都是在RedisAutoConfiguration类内给我们注入好的，我们直接引用就可以了
	@Autowired
	StringRedisTemplate stringRedisTemplate;  //用来操作k-v都是字符串的

	@Autowired
	RedisTemplate redisTemplate;  //用来操作k-v都是对象的

	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;


	/**
	 * Redis常见的五大数据类型
	 *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
	 *  stringRedisTemplate.opsForValue()：操作String（字符串）
	 *  stringRedisTemplate.opsForList()：操作List（列表）
	 *  stringRedisTemplate.opsForSet()：操作Set（集合）
	 *  stringRedisTemplate.opsForHash()：操作Hash（散列）
	 *  stringRedisTemplate.opsForZSet()：操作ZSet（有序集合）
	 * 后面可以继续追加使用redis中的那些命令。
	 */
	@Test
	public void test01(){
		//给redis中保存数据
	    //stringRedisTemplate.opsForValue().append("msg","hello");
//		String msg = stringRedisTemplate.opsForValue().get("msg");
//		System.out.println(msg);

//		stringRedisTemplate.opsForList().leftPush("mylist","1");
//		stringRedisTemplate.opsForList().leftPush("mylist","2");
	}

	//测试保存对象
	@Test
	public void test02(){
		Employee empById = employeeMapper.getEmpById(1);
		//如果保存对象，默认使用jdk序列化机制(JdkSerializationRedisSerializer)，序列化后的数据保存到redis中。
		//使用redis管理工具去查看，发现里面的键值对被序列化成一长串编码，比较难看。可以将数据以json的方式保存
		//redisTemplate.opsForValue().set("emp-01",empById);//（这里的Employee就需要序列化一下）
		
		//1、将数据以json的方式保存（自己编写一个redis配置类去配置，比如这里的MyRedisConfig类）
		 //(1)将对象转为json
		 //(2)改变redisTemplate默认的序列化规则。
		empRedisTemplate.opsForValue().set("emp-01",empById);
	}

	@Test
	public void contextLoads() {

		Employee empById = employeeMapper.getEmpById(1);
		System.out.println(empById);

	}

}
