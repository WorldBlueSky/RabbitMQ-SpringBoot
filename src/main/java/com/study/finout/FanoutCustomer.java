package com.study.finout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutCustomer {

    // 消费者1 ，声明临时队列、声明交换机、绑定两者
    @RabbitListener(
            bindings = {
                    @QueueBinding(value = @Queue,exchange = @Exchange(value = "aaa",type = "fanout"))
            }
    )
    public void receive(String message){
        System.out.println();
        System.out.println("消费者1 ："+message);
        System.out.println();
    }

    // 消费者2，声明临时队列、声明交换机、绑定两者
    @RabbitListener(
            bindings = {
                    @QueueBinding(value = @Queue,exchange = @Exchange(value = "aaa",type = "fanout"))
            }
    )
    public void receive2(String message){
        System.out.println();
        System.out.println("消费者2 ："+message);
        System.out.println();
    }


}
