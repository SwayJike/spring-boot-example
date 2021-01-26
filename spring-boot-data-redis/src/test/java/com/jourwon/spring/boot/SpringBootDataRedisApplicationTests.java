package com.jourwon.spring.boot;

import com.jourwon.spring.boot.dto.NoticeMessage;
import com.jourwon.spring.boot.dto.User;
import com.jourwon.spring.boot.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class SpringBootDataRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    //===============RedisUtils测试===============

    @Test
    public void testValueOperations01() {
        System.out.println(redisUtils.delete("key"));
        System.out.println(redisUtils.hasKey("key"));
        System.out.println(redisUtils.set("key", "value"));
        System.out.println(redisUtils.get("key"));
        System.out.println(redisUtils.hasKey("key"));
        System.out.println(redisUtils.getExpire("key"));
        System.out.println(redisUtils.getExpire("key1"));
    }

    @Test
    public void testValueOperations02() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("JourWon");
        user.setCreateTime(LocalDateTime.now());

        System.out.println(redisUtils.set("user", user));
        System.out.println(((User)redisUtils.get("user")).toString());
    }


    //===============发布订阅测试===============
    @Test
    public void test3() {
        //convertAndSend(String channel, Object message)
        //channel:主题名称，即是要发送的频道，message:消息体
        redisTemplate.convertAndSend("topicName", new NoticeMessage("DT", "hello world!"));
        System.out.println("发布者发送消息！");
    }

}
