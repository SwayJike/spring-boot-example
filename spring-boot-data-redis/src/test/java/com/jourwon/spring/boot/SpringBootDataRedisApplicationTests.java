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
import java.util.*;

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
        System.out.println(redisUtils.increment("int1", 2L));
        System.out.println("========");

        System.out.println(redisUtils.set("int2", "1"));
        System.out.println(redisUtils.get("int2"));
        System.out.println(redisUtils.decrement("int2"));
        System.out.println("========");

        System.out.println(redisUtils.set("int3", "1"));
        System.out.println(redisUtils.get("int3"));
        System.out.println(redisUtils.decrement("int3", 2L));
    }

    @Test
    public void testHashOperations01() {
        System.out.println(redisUtils.hPut("hkey", "key", "value"));
        System.out.println(redisUtils.hPut("hkey1", "key1", "value1", 60L));
        System.out.println(redisUtils.hHasKey("hkey", "key"));
        System.out.println(redisUtils.hHasKey("hkey1", "key1"));
        System.out.println(redisUtils.hget("hkey", "key"));
        System.out.println(redisUtils.hget("hkey1", "key1"));
        System.out.println(redisUtils.hKeys("hkey"));
        System.out.println(redisUtils.hKeys("hkey1"));
        System.out.println(redisUtils.hEntries("hkey"));
        System.out.println(redisUtils.hEntries("hkey1"));
        System.out.println("========");

        Map<String, String> map = new HashMap<>(8);
        map.put("key1", "value1");
        map.put("key2", "value2");
        System.out.println(redisUtils.hPutAll("map", map));
        System.out.println(redisUtils.hPutAll("map1", map, 60L));
        System.out.println(redisUtils.hHasKey("map", "key1"));
        System.out.println(redisUtils.hHasKey("map1", "key1"));
        System.out.println(redisUtils.hget("map", "key1"));
        System.out.println(redisUtils.hget("map1", "key1"));
        System.out.println(redisUtils.hKeys("map"));
        System.out.println(redisUtils.hKeys("map1"));
        System.out.println(redisUtils.hEntries("map"));
        System.out.println(redisUtils.hEntries("map1"));
    }

    @Test
    public void testHashOperations02() {
        System.out.println(redisUtils.hPut("hkey", "key", "1"));
        System.out.println(redisUtils.hIncrement("hkey", "key", 1D));
        System.out.println(redisUtils.hMultiGet("hkey", Collections.singletonList("key")));
        System.out.println(redisUtils.hDelete("hkey", "key"));
        System.out.println("========");

        Map<String, String> map = new HashMap<>(8);
        map.put("key1", "1");
        map.put("key2", "1");
        System.out.println(redisUtils.hPutAll("map", map));
        System.out.println(redisUtils.hIncrement("map", "key1", 1D));
        System.out.println(redisUtils.hMultiGet("map", Arrays.asList("key1", "key2")));
        System.out.println(redisUtils.hDelete("map", "key1", "key2"));
    }

    @Test
    public void testHashOperations03() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("JourWon");
        user.setMobilePhoneNumber("13800000000");
        user.setEmail("JourWon@163.com");
        user.setDeleteState((short) 0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(new Date());

        System.out.println(redisUtils.hPut("huser", "user", user));
        System.out.println(redisUtils.hget("huser", "user", User.class));
    }

    @Test
    public void testListOperations01() {
        System.out.println(redisUtils.lSet("lkey", 0, "lvalue"));
        System.out.println(redisUtils.lSet("lkey", 1, "lvalue1"));
        System.out.println(redisUtils.lSet("lkey", 2, "lvalue2"));
        System.out.println(redisUtils.lIndex("lkey", 0L));
        System.out.println(redisUtils.lSize("lkey"));
        System.out.println(redisUtils.lRange("lkey", 0L, -1L));
        System.out.println("========");

        System.out.println(redisUtils.lRightPush("lkey1", "lvalue1"));
        System.out.println(redisUtils.lRightPush("lkey1", "lvalue2", 60L));
        System.out.println(redisUtils.lSize("lkey1"));
        System.out.println(redisUtils.lRange("lkey1", 0L, -1L));
        System.out.println("========");

        System.out.println(redisUtils.lRightPushAll("lkey2", Arrays.asList("1", "2", "3")));
        System.out.println(redisUtils.lRange("lkey2", 0L, -1L));
    }

    @Test
    public void testListOperations02() {
        System.out.println(redisUtils.lRightPushAll("lkeyAll", Arrays.asList("1", "2", "1", "1")));
        System.out.println(redisUtils.lRange("lkeyAll"));
        System.out.println(redisUtils.lRightPushAll("lkeyAll1", Arrays.asList("1", "2"), 60L));

        System.out.println(redisUtils.lRemove("lkeyAll", 2, "1"));
        System.out.println(redisUtils.lRange("lkeyAll"));
    }

    @Test
    public void testSetOperations01() {
        System.out.println(redisUtils.sAdd("skey","1","1","2"));
        System.out.println(redisUtils.sIsMember("skey","2"));
        System.out.println(redisUtils.sMembers("skey"));
        System.out.println(redisUtils.sSize("skey"));
        System.out.println(redisUtils.sRemove("skey","2"));

        System.out.println(redisUtils.sAdd("skey1",60L,"1","1","2"));
    }

    @Test
    public void testZSetOperations01() {
        System.out.println(redisUtils.zAdd("zkey","zvalue",1D));
        System.out.println(redisUtils.zAdd("zkey","zvalue1",2D));
        System.out.println(redisUtils.zAdd("zkey","zvalue2",3D));
        System.out.println(redisUtils.zScore("zkey","zvalue2"));
        System.out.println(redisUtils.zCard("zkey"));
        System.out.println("========");

        System.out.println(redisUtils.zRangeByScore("zkey",-1D,2D));
        System.out.println("========");

        System.out.println(redisUtils.zRange("zkey"));
        System.out.println("========");

        System.out.println(redisUtils.zAdd("zkey","zvalue",4D));
        System.out.println(redisUtils.zRange("zkey"));

        System.out.println(redisUtils.zRemove("zkey","zvalue2"));
        System.out.println(redisUtils.zRange("zkey"));
    }

    //===============发布订阅测试===============
    @Test
    public void test3() {
        //channel:主题名称，即是要发送的频道，message:消息体
        redisTemplate.convertAndSend("topicName", ObjectMapperUtils.objToJson(new NoticeMessage("notice", "hello world!")));
        System.out.println("==发布者发送消息==");
    }

}
