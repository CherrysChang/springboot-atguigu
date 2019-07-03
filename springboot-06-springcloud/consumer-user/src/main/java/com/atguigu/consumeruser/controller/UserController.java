package com.atguigu.consumeruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;//帮我们发送http请求

    @GetMapping("/buy")
    public String buyTicket(String name){//消费服务
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);//http+服务名+请求服务地址，第二个参数是返回值类型
        return name+"购买了"+s;
    }
}
