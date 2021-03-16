package com.tensquare.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sun.java2d.pipe.ValidatePipe;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.rabbitmq.listener
 * @author:MartinKing
 * @createTime:2021/3/14 19:08
 * @version:1.0
 */
@Component
@RabbitListener(queues = "itheima")
public class Customer2 {
    @RabbitHandler
    public void receiveMsg(String msg) {
        System.out.println("消费者2-itheima-接收到的数据为："+msg);
    }
}
