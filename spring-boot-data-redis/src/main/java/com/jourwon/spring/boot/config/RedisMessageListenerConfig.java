package com.jourwon.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Redis消息监听配置类
 *
 * @author JourWon
 * @date 2021/1/18
 */
@Component
public class RedisMessageListenerConfig {

    /**
     * 主题名称（频道名称）
     */
    public static final String TOPIC_NAME = "topicName";

    /**
     * 配置Redis消息的监听容器
     *
     * @param connectionFactory 连接工厂
     * @param messageListenerAdapter 消息监听器适配器
     * @return RedisMessageListenerContainer Redis消息监听器容器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 配置主题名称:topicName
        container.addMessageListener(messageListenerAdapter, new PatternTopic(TOPIC_NAME));
        return container;
    }

}
