package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange(){//AmqpAdmin的使用：创建和删除 Queue（消息队列），Exchange（交换器），Binding（绑定消息队列和交换器之间的关联）
		//以declare开头的都是创建一些组件，以remove、delete开头的都是删除一些组件
		//创建交换器
//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		System.out.println("创建完成");
		
		//创建消息队列
//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));//队列的名字，是否持久化
		
		//创建绑定规则（下面Binding传参：目的地、目的地类型：QUEUE或者EXCHANGE、交换器名字、路由键、参数头等）
//		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

		//amqpAdmin.。。。。
	}

	/**
	 * 1、单播（点对点）
	 */
	@Test
	public void contextLoads() {
		//①、rabbitTemplate.send这种方式好处：Message需要自己构造一个。可以定义消息体内容和消息头
		//rabbitTemplate.send(exchage,routeKey,message);//参数：交换器、路由键、消息

		//②、rabbitTemplate.convertAndSend方式是转换并发送。object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
		//如果不涉及复杂的消息头，可以采用这个方法。
		//rabbitTemplate.convertAndSend(exchage,routeKey,object);//参数：交换器、路由键、数据对象
		Map<String,Object> map = new HashMap<>();
		map.put("msg","这是第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		//对象被默认序列化以后发送出去
		//rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));

	}

	//接受数据。问题：如何将数据自动的转为json发送出去？可以在配置类中重新写一个MessageConvert(类型是amqp.support.converter)。具体参见MyAMQPConfig 类
	@Test
	public void receive(){//接受消息后，消息队列中就没有该消息了。
		//rabbitTemplate.receive接受转成Message,Message里只有消息头，没有消息体。
		//rabbitTemplate.receiveAndConvert 接受并转换成Object。
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o.getClass());//上面发送的HashMap对象，这边接受的就是HashMap类型
		System.out.println(o);//{msg=这是一个消息,data=[hello,123,true]}
	}

	/**
	 * 广播
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
	}

}
