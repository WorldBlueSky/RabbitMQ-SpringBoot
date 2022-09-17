package com.study.ttl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

@RestController
@RequestMapping("/ttl")
@Slf4j
public class TtlProduce {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 生产者 发送一个延迟消息，相当于给rabbitmq发送一个消息
    @RequestMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){

        log.info("{} 发送一条消息到两个TTL队列: {}",new Date(),message);

        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl:10s的队列："+message );
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl:40s的队列："+message );
    }

}
