package com.jourwon.spring.boot;

import com.jourwon.spring.boot.publisher.CustomSpringEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootApplicationeventApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private CustomSpringEventPublisher eventPublisher;

    @Test
    public void publishTest() {
        eventPublisher.publishEvent("发布消息");
    }

}
