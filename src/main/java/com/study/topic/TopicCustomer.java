package com.study.topic;

import com.rabbitmq.client.ConfirmCallback;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

@Component
public class TopicCustomer {


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 声明临时队列
                    exchange = @Exchange(value = "ccc",type = "topic"),
                    key = "produce.#"
            )
    })
    public void receive(String message){
        System.out.println();
        System.out.println("消费者1 produce.# ::: "+message);
        System.out.println();
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 声明临时队列
                    exchange = @Exchange(value = "ccc",type = "topic"),
                    key = "produce.*"
            )
    })
    public void receive2(String message){
        System.out.println();
        System.out.println("消费者2 produce.* ::: "+message);
        System.out.println();
    }

}
