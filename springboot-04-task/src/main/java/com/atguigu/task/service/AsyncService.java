package com.atguigu.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
* 异步任务在我们开发中比较常见，比如发送邮件或处理一些数据，不希望它阻塞下面的线程，就可以用多线程的方式进行异步处理。
*/
@Service
public class AsyncService {

    //告诉Spring这是一个异步方法。（spring自己就会开启一个线程池来进行调用）
    //@Async注解起作用的前提是在SpringBoot主配置类上加一个@EnableAsync来开启异步注解功能
    @Async//ps：如果不使用异步方式，即同步，那么请求就需要等待3秒才回返回结果。如果使用异步，请求会立即响应，处理数据也会被调用。
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中...");
    }
}
