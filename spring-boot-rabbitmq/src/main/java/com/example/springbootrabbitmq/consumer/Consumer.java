package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title:Consumer
 * @Author SwayJike
 * @Date:2021/12/27 22:19
 * @Version 1.0
 */
@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = {ConfirmConfig.CONFIRM_QUEUE_NAME})
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody());
        log.info("接收到的队列{}消息: {}", ConfirmConfig.CONFIRM_QUEUE_NAME, msg);
    }
}
