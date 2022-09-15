package com.study.test;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Test {

    // 消费者1，qq1 队列绑定exchangeName交换机、info路由
    @RabbitListener(queuesToDeclare = @Queue("qq1"))
    public void test1(String message){
     System.out.println("消费者1：info 路由======="+message);
    }

    // 消费者2 ， qq2 队列绑定exchangeName 交换机， error路由
    @RabbitListener(queuesToDeclare = @Queue("qq2"))
    public void test2(String message){
        System.out.println("消费者2：error 路由======="+message);
    }
}
