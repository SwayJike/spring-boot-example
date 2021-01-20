package com.jourwon.spring.boot;

import com.jourwon.spring.boot.dto.NoticeMessage;
import com.jourwon.spring.boot.dto.User;
import com.jourwon.spring.boot.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootDataRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test() {
        User user = new User("DT小白", 22);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    public void test1() {
        redisUtils.set("dt", "ydf");
        System.out.println(redisUtils.get("dt"));
    }

    @Test
    public void test2() {
        System.out.println(redisUtils.hasKey("dt"));
    }

    //=======================发布订阅测试===============
    @Test
    public void test3() {
        //convertAndSend(String channel, Object message)
        //channel:主题名称，即是要发送的频道，message:消息体
        redisTemplate.convertAndSend("topicName", new NoticeMessage("DT", "hello world!"));
        System.out.println("发布者发送消息！");
    }

}
