package com.study.betterttl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/ttl")
@Slf4j
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //    基于插件 发送消息以及时间
    @RequestMapping("/sendDelayMag/{message}/{delayTime}")
    public void sendMsg(@PathVariable String message,@PathVariable Integer delayTime){

        System.out.println(delayTime);

        // 一条消息延迟时间的条件设置
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 这里以前是 setExpire 相当于设置过期时间
                // 使用插件的时候，我们需要使用setDeLay 设置延迟时间,在接收参数 delayTime 的时候就写成Integer 自动转化类型
                message.getMessageProperties().setDelay(delayTime);
                return message;
            }
        };

        log.info("当前时间为：{}, 发送一条时间为 {} 的消息为给延迟队列",new Date(),delayTime);

        // 生产者发送第一条消息，延时20s
        rabbitTemplate.convertAndSend("delayed.exchange", "delayed.routineKey",message,processor);


    }
}
