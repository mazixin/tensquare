package com.mq.test;

import com.tensquare.rabbitmq.MQapplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.mq.test
 * @author:MartinKing
 * @createTime:2021/3/14 18:40
 * @version:1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MQapplication.class)
public class DirectTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void DirectSend() {
        rabbitTemplate.convertAndSend("itcast","这是测试的消息");
    }

    @Test
    public void FanoutSend() {
        rabbitTemplate.convertAndSend("chuanzhi","","Fanout分裂模式发送的消息");
    }

    @Test
    public void TopicTest() {
        rabbitTemplate.convertAndSend("topictest","goods.log","主题模式");
    }
}
