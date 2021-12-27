package com.example.springbootrabbitmq.config;

import com.sun.javafx.tk.TKPulseListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title:ConfirmConfig
 * @Author SwayJike
 * @Date:2021/12/27 22:03
 * @Version 1.0
 */
@Configuration
public class ConfirmConfig {

    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";

    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";

    public static final String CONFIRM_ROUTING_KEY = "confirm_key";

    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";

    public static final String BACKUP_QUEUE_NAME = "backup_queue";

    public static final String WARNING_QUEUE_NAME = "warning_queue";

    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true)
                .withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME).build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    @Bean
    public Binding queueBindingExchange(){
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with(CONFIRM_ROUTING_KEY);
    }

    @Bean("backupExchange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean("backupQueue")
    public Queue backupQueue(){
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    @Bean("warningQueue")
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding backupQueueBindingBackupExchange(){
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }

    @Bean
    public Binding warningQueueBindingBackupExchange(){
        return BindingBuilder.bind(warningQueue()).to(backupExchange());
    }
}
