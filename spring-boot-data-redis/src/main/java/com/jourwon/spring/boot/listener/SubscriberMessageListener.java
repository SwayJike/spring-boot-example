package com.jourwon.spring.boot.listener;

import com.jourwon.spring.boot.dto.NoticeMessage;
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
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String topic = (String) redisTemplate.getStringSerializer().deserialize(channel);
        assert topic != null;
        if (!topic.equals(TOPIC_NAME)) {
            return;
        }
        Object res = redisTemplate.getValueSerializer().deserialize(body);
        // 如果反序列化得到的是我们定义的消息数据体类型
        if (res instanceof NoticeMessage) {
            NoticeMessage noticeMessage = (NoticeMessage) res;
            System.out.println("==订阅者1收到消息==");
            System.out.println(noticeMessage);
        } else {
            System.out.println("==其他业务处理==");
        }
    }

}
