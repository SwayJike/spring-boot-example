package com.jourwon.spring.boot.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息订阅监听器
 *
 * @author JourWon
 * @date 2021/1/18
 */
@Component
public class SubscriberMessageListener extends MessageListenerAdapter {

    /**
     * 主题名称（频道名称）
     */
    public static final String TOPIC_NAME = "topicName";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
        assert topic != null;
        if (!topic.equals(TOPIC_NAME)) {
            return;
        }
        Object obj = redisTemplate.getValueSerializer().deserialize(body);
        System.out.println("==订阅者1收到消息==");
        System.out.println(obj);
    }

}
