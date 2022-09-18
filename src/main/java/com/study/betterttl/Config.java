package com.study.betterttl;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    // 延时交换机
    public static final String delayed_exchange = "delayed.exchange";

    // 延时 routingKey
    public static final String delayed_Routing_Key = "delayed.routineKey";

    // 延时队列
    public static final String delayed_queue_name = "delayed.queue";

    // 只有fanoutExchange、directExchange、topicExchange等配置类，我们只能自定义交换机类型为延时交换机
    @Bean
    public CustomExchange deadExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");// 该交换机采用的模式依然是direct
        return new CustomExchange(delayed_exchange, "x-delayed-message", true, false, args);
    }

    // 这是一个普通正常的队列
    @Bean
    public Queue deadQueue(){
        return new Queue(delayed_queue_name, true, false, false, null);
    }

    // 普通队列 绑定 延时交换机与路由
    @Bean
    public Binding binding(@Qualifier("deadExchange") Exchange deadExchange,@Qualifier("deadQueue") Queue deadQueue){
        // 这里一定要加 noargs（），表示没有参数了，必须加
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(delayed_Routing_Key).noargs();
    }
}
