package com.study.ttl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;
import java.util.Date;

@Slf4j
@Component
public class ttlCustomer {

     // 监听 QA队列，对其中的消息进行 业务消费
    @RabbitListener(queues = "QA")
     public void qa(Message message){
     String msg = new String(message.getBody());
     log.info("{} 收到QA队列的消息是 {}",new Date().toString(),msg);
     }

     // 监听 QB队列，对其中的消息进行 业务消费
    @RabbitListener(queues = "QB")
     public void qb(Message message){
     String msg = new String(message.getBody());
     log.info("{} 收到QB队列的消息是 {}",new Date().toString(),msg);
     }

    // 监听 死信 QD队列，对其中的消息进行 业务消费
    @RabbitListener(queues = "QD")
    public void qDead(Message message){

        String msg = new String(message.getBody());
        log.info("{} 收到死信队列的消息是 {}",new Date().toString(),msg);


    }



}
