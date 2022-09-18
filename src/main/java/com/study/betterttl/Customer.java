package com.study.betterttl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Customer {

    // 监听 延时队列的消息
    @RabbitListener(queues = Config.delayed_queue_name)
    public void receive(String message){
        log.info("当前时间为：{} ,收到延时队列的消息：{}",new Date(),message);
    }

}
