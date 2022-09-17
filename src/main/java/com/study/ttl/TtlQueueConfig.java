package com.study.ttl;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlQueueConfig {

    // 普通交换机名称
    public static final String X_Exchange= "X";

    // 死信交换机名称
    public static final String Y_Dead_Letter_Exchange= "Y";

    // 普通队列名称
    public static final String Queue_A= "QA";
    public static final String Queue_B= "QB";

    // 死信队列名称
    public static final String Dead_Letter_Queue_D="QD";

    // 声明普通交换机
    @Bean
    public DirectExchange xExchange(){
        return new DirectExchange(X_Exchange);
    }

    // 声明普通队列QA
    @Bean
    public Queue qa(){

        Map<String,Object> args =new HashMap<>();
        // 设置过期时间 10s
        args.put("x-message-ttl",10000);
        // 设置死信交换机
        args.put("x-dead-letter-exchange", Y_Dead_Letter_Exchange);
        // 设置死信routineKey
        args.put("x-dead-letter-routing-key", "YD");

        return new Queue(Queue_A, true, false, false, args);
    }

    // 声明普通队列QB
    @Bean
    public Queue qb(){

        Map<String,Object> args =new HashMap<>();
        // 设置过期时间 40s
        args.put("x-message-ttl",40000);
        // 设置死信交换机
        args.put("x-dead-letter-exchange", Y_Dead_Letter_Exchange);
        // 设置死信routineKey
        args.put("x-dead-letter-routing-key", "YD");

        return new Queue(Queue_B, true, false, false, args);
    }

    // 绑定 普通交换机与 队列QA
    @Bean
    public Binding queueABindX(){
        // 交换机X 路由key-->XA  绑定队列 QA
        return BindingBuilder.bind(qa()).to(xExchange()).with("XA");
    }

    // 绑定 普通交换机与 队列QB
    @Bean
    public Binding queueBBindX(){
        // 交换机X 路由key-->XB  绑定队列 QB
        return BindingBuilder.bind(qb()).to(xExchange()).with("XB");
    }

    //声明死信交换机
    @Bean
    public DirectExchange yDeadExchange(){
        return new DirectExchange(Y_Dead_Letter_Exchange);
    }

    // 声明死信队列
    @Bean
    public Queue deadQueueD(){
        return new Queue(Dead_Letter_Queue_D, true, false, false, null);
    }

    // 绑定死信交换机与死信队列
    @Bean
    public Binding queueDBindYExchange(){
        // 绑定 死信交换机Y 路由key YD  与死信队列 QD
        return BindingBuilder.bind(deadQueueD()).to(yDeadExchange()).with("YD");
    }

}
