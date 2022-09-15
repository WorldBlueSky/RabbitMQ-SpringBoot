package com.study.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

@Component
public class WorkCustomer {

    @RabbitListener(
            queuesToDeclare = @Queue("work")  // 消费者1 声明队列，与生产者一致
    )
    public void receive(String message){
        System.out.println("消费者1 拿到消息："+message);
    }

    @RabbitListener(
            queuesToDeclare = @Queue("work") // 消费者2 声明队列，与生产者一致
    )
    public void receive2(String message){
        System.out.println("消费者2 拿到消息："+message);
    }


}
