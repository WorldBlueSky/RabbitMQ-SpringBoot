package com.study.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectCustomer {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue,// 声明临时队列
                            exchange = @Exchange(value = "bbb",type = "direct"),// 声明交换机，默认是 direct
                            key = {"info"}  // 绑定 路由key
                    )
            }
    )
    public void receive(String message){
        System.out.println();
        System.out.println("消费者1可接收{’info‘} ："+message);
        System.out.println();
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue,// 声明临时队列
                            exchange = @Exchange(value = "bbb",type = "direct"),// 声明交换机，默认是 direct
                            key = {"info","warning","error"}// 绑定 路由key
                    )
            }
    )
    public void receive2(String message){
        System.out.println();
        System.out.println("消费者2可接收{'info','warning','error'} ："+message);
        System.out.println();
    }

}
