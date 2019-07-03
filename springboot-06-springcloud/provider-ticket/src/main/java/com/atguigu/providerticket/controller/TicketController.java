package com.atguigu.providerticket.controller;

import com.atguigu.providerticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//SpringCloud在整合微服务时是通过轻量级http进行通信的。
@RestController
public class TicketController {


    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket")
    public String getTicket(){//这里通过http的形式将服务接口暴露出来
        return ticketService.getTicket();
    }
}
