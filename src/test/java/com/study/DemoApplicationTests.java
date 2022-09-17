package com.study;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        // 生产者 使用 helloworld 模型 点对点的发送队列消息
        rabbitTemplate.convertAndSend("simple","这是 helloworld 模型发布的消息!");
    }

    @Test
    public void work(){
        // 生产者 使用work 公平竞争机制 发送消息
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("", "work", "这是 work 模型发布的信息"+i);
        }
    }

    @Test
    public void finout(){
        // 生产者使用 fanout 广播模型 发送消息，只要订阅了该交换机都能收到消息
        rabbitTemplate.convertAndSend("aaa", "", "广播模型发布的消息");
    }

    @Test
    public void direct(){
        // 生产者使用 路由模型中的 direct 发送消息
        rabbitTemplate.convertAndSend("bbb", "info", "info生产者消息");
    }

    @Test
    public void topic1(){
        // 生产者使用 路由模型中的 topic 发送消息
        rabbitTemplate.convertAndSend("ccc", "produce", "生产者 produce路由 消息");
    }

    @Test
    public void topic2(){
        rabbitTemplate.convertAndSend("ccc", "produce.username", "生产者 produce.username路由 消息");
    }

    @Test
    void test(){
       rabbitTemplate.convertAndSend("e1", "", " 生产者广播的消息!");
    }

    // 使用阻塞队列线程执行的 版本 ， 生产者消费者模型
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(){
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++) {
                        blockingQueue.put(i);
                        System.out.println("生产了元素："+i);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread customer = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Integer value = blockingQueue.take();
                        System.out.println("消费了元素："+value);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        customer.start();

        // 主线程等待两个子线程执行完
        try {
            producer.join();
            customer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testtt(){
        rabbitTemplate.convertAndSend("exchangeName", "info", "使用配置类声明进行发送消息!");
    }
    @Test
    public void a(){

        // 这个匿名函数可以设置本条发送消息的一些配置信息
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                return message;
            }
        };

        rabbitTemplate.convertAndSend("","normalQueue","本条消息5s后过期!",processor);
    }

    @Test
    public void dead(){
        // 使用点对点模型，然后中间队列中的消息过期进入死信队列中，死信队列消费者对其进行消费
        rabbitTemplate.convertAndSend("deadQueue","过期时间的消息!");
    }

    @Test
    public void aaaa1(){
           rabbitTemplate.convertAndSend("queue1", "发送消息");
    }

    @Test
    public void aaaa(){
        System.out.println(new Date());
    }

}
