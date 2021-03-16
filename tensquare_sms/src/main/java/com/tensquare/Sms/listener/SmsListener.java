package com.tensquare.Sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.Sms.listener
 * @author:MartinKing
 * @createTime:2021/3/15 18:54
 * @version:1.0
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @RabbitHandler
    public void sendSms(Map<String, String> map) {
        String mobile = map.get("mobile");
        String checkCode = map.get("checkCode");
        System.out.println("手机号为"+mobile);
        System.out.println("验证码为"+checkCode);
    }
}
