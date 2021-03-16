package com.tensquare.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.rabbitmq.listener
 * @author:MartinKing
 * @createTime:2021/3/14 18:44
 * @version:1.0
 */
@Component
@RabbitListener(queues = "itcast")
public class Customer1 {

    @RabbitHandler
    public void showMessage(String message) {
        System.out.println("用户1-itcast-接收到的消息为:"+message);
    }
}
