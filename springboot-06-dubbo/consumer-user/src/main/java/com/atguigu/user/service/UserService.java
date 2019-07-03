package com.atguigu.user.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service//这个是Spring的注解，不是dubbo的
public class UserService{

    @Reference //dubbo注解，远程引用（发布时按照对应接口的全类名发布的，这边引用就可以获取到。前提这个要跟那个发布的全类名一致）
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票了："+ticket);
    }


}
