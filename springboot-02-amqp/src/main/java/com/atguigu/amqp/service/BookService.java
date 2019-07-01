package com.atguigu.amqp.service;

import com.atguigu.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    /**
    * @RabbitListener注解监听消息队列。如果需要该注解起作用，需要开启基于注解的Rabbit模式。使用@EnableRabbit注解。作用在SpringBoot的主配置类上
    * 只要监听的消息队列有内容进来，该方法就会被调用
    */
    @RabbitListener(queues = "atguigu.news")//queues是数组的形式，可以监听多个消息队列
    public void receive(Book book){//这里直接将消息反序列化成Book对象
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive02(Message message){//对于定制的消息，使用message接受，可以得到消息头
        System.out.println(message.getBody());//消息内容
        System.out.println(message.getMessageProperties());//消息头信息
    }
}
