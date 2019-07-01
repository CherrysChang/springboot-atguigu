package com.atguigu.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAMQPConfig {

    @Bean
    public MessageConverter messageConverter(){//重新注册一个messageConverter。默认的就会失效，采用当前这个
        return new Jackson2JsonMessageConverter();//使用json相关的实现类。具体实现可以对messageConverter接口进行ctrl+H查看
    }
}
