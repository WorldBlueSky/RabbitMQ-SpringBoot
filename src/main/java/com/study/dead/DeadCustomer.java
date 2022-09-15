package com.study.dead;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeadCustomer {

    // 这个类监听死信队列，进行消费
    @RabbitListener(queuesToDeclare = @Queue("line"))
    public void receive(String message){
        System.out.println("========================================");
        System.out.println("这是死信队列中的消息： "+message);
        System.out.println("========================================");
    }
}
