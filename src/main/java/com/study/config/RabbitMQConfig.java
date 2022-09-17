//package com.study.config;
//
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Bean
//    public Queue deadQueue(){
//        Map<String,Object>  args = new HashMap<>();
//        // 设置过期时间、过期后指定转移的 死信交换机、路由key
//        args.put("x-message-ttl", 1);
//        args.put("x-dead-letter-exchange", "dead");
//        args.put("x-dead-letter-routing-key", "info");
//        return new Queue("deadQueue", true, false, false, args);
//    }
//
//    // 声明死信交换机
//    @Bean
//    public DirectExchange deadExchange(){
//        return new DirectExchange("dead");
//    }
//
//    // 声明死信队列
//    @Bean
//    public Queue lineQueue(){
//        return new Queue("line");
//    }
//
//    // 死信队列 绑定死信交换机以及路由key
//    @Bean
//    public Binding lineBind(){
//        return BindingBuilder.bind(lineQueue()).to(deadExchange()).with("info");
//    }
//
//
//    @Bean
//    public Queue queue11(){
//        // 并没有设置该队列是过期队列
//        return new Queue("normalQueue", true, false, false, null);
//    }
//
//    @Bean
//    public Queue ttlQueue(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("x-message-ttl", 5000);
//        return new Queue("ttlQueue", true, false, false, map);
//    }
//
//    // 声明 direct 模式的交换机，与生产者一致
//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange("exchangeName", true, false, null);
//    }
//
//    // 声明接收的队列1
//    @Bean
//    public Queue qq1(){
//        return new Queue("qq1", true, false, false, null);
//    }
//
//    // 声明接收的队列2
//    @Bean
//    public Queue qq2(){
//        return new Queue("qq2", true, false, false, null);
//    }
//
//
//    // 声明绑定关系,qq1 队列 与 交换机1 info进行绑定
//    @Bean
//    public Binding binding(){
//        return BindingBuilder.bind(qq1()).to(directExchange()).with("info");
//    }
//
//    // 声明绑定关系， qq2 队列 与交换机1 error 进行绑定
//    @Bean
//    public Binding binding111(){
//        return BindingBuilder.bind(qq2()).to(directExchange()).with("error");
//    }
//
//
//
//    // 声明广播 fanout 模式的交换机
//    @Bean
//    public FanoutExchange exch1(){
//        return new FanoutExchange("e1");
//    }
//
//    // 声明队列
//    @Bean
//    public Queue q1(){
//        return new Queue("q1", true, false, false, null);
//    }
//    // 声明队列
//    @Bean
//    public Queue q2(){
//        return new Queue("q2", true, false, false, null);
//    }
//
//
//    // 绑定关系
//    @Bean
//    public Binding binding1(){
//        return BindingBuilder.bind(q1()).to(exch1());
//    }
//    // 绑定关系
//    @Bean
//    public Binding binding2(){
//        return BindingBuilder.bind(q2()).to(exch1());
//    }
//
//
//}
//
