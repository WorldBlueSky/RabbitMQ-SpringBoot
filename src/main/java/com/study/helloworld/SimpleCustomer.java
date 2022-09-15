package com.study.helloworld;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("simple"))
public class SimpleCustomer {


    @RabbitHandler  // 表示从队列中取出消息进行业务处理
    public void received(String message){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

}
