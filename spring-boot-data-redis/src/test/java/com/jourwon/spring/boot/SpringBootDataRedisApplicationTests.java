package com.jourwon.spring.boot;

import com.jourwon.spring.boot.dto.NoticeMessage;
import com.jourwon.spring.boot.dto.User;
import com.jourwon.spring.boot.util.ObjectMapperUtils;
import com.jourwon.spring.boot.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest
class SpringBootDataRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    //===============RedisUtils测试===============

    @Test
    public void testRedisOperations01() {
        System.out.println(redisUtils.delete(Arrays.asList("key", "key1")));
        System.out.println(redisUtils.delete("key"));
        System.out.println(redisUtils.hasKey("key"));
        System.out.println(redisUtils.set("key", "value"));
        System.out.println(redisUtils.set("key1", "value1", 60L));
        System.out.println(redisUtils.get("key"));
        System.out.println(redisUtils.get("key1"));
        System.out.println(redisUtils.hasKey("key"));
        System.out.println(redisUtils.hasKey("key1"));
        System.out.println(redisUtils.getExpire("key"));
        System.out.println(redisUtils.getExpire("key1"));
    }

    @Test
    public void testValueOperations01() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("JourWon");
        user.setMobilePhoneNumber("13800000000");
        user.setEmail("JourWon@163.com");
        user.setDeleteState((short) 0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(new Date());

        System.out.println(redisUtils.set("user", user));
        System.out.println(redisUtils.get("user", User.class));
    }

    @Test
    public void testValueOperations02() {
        System.out.println(redisUtils.set("int", "1"));
        System.out.println(redisUtils.get("int"));
        System.out.println(redisUtils.increment("int"));
        System.out.println("========");

        System.out.println(redisUtils.set("int1", "1"));
        System.out.println(redisUtils.get("int1"));
        System.out.println(redisUtils.increment("int1",2L));
        System.out.println("========");

        System.out.println(redisUtils.set("int2", "1"));
        System.out.println(redisUtils.get("int2"));
        System.out.println(redisUtils.decrement("int2"));
        System.out.println("========");

        System.out.println(redisUtils.set("int3", "1"));
        System.out.println(redisUtils.get("int3"));
        System.out.println(redisUtils.decrement("int3",2L));
    }


    //===============发布订阅测试===============
    @Test
    public void test3() {
        //channel:主题名称，即是要发送的频道，message:消息体
        redisTemplate.convertAndSend("topicName", ObjectMapperUtils.objToJson(new NoticeMessage("notice", "hello world!")));
        System.out.println("==发布者发送消息==");
    }

}
