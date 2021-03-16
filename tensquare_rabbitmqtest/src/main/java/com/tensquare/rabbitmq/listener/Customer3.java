package com.tensquare.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.rabbitmq.listener
 * @author:MartinKing
 * @createTime:2021/3/14 19:12
 * @version:1.0
 */
@Component
@RabbitListener(queues = "kudingyu")
public class Customer3 {
    @RabbitHandler
    public void receiveMsg(String msg) {
        System.out.println("消费者3-kudingyu-接收到的消息为"+msg);
    }
}
