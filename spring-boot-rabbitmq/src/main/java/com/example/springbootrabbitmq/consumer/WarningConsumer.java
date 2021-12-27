package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title:WarningConsumer
 * @Author SwayJike
 * @Date:2021/12/27 23:58
 * @Version 1.0
 */
@Slf4j
@Component
public class WarningConsumer {

    @RabbitListener(queues = {ConfirmConfig.WARNING_QUEUE_NAME})
    public void receiveWarningMessage(Message message) {
        String msg = new String(message.getBody());
        log.error("报警发现不可路由消息: {}", msg);
    }
}
