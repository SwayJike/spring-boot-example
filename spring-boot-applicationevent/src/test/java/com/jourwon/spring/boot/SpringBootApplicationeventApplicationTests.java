package com.jourwon.spring.boot;

import com.jourwon.spring.boot.publisher.CustomSpringEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author JourWon
 * @date 2021/11/28
 */
@SpringBootTest
public class SpringBootApplicationeventApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Resource
    private CustomSpringEventPublisher eventPublisher;

    @Test
    public void publishTest() {
        eventPublisher.publishEvent("发布消息");
    }

}
