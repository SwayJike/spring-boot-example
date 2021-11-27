package com.jourwon.spring.boot.publisher;

import com.jourwon.spring.boot.event.CustomSpringEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 创建事件的发布者 注入ApplicationEventPublisher
 *
 * @author JourWon
 * @date 2021/11/27
 */
@Component
public class CustomSpringEventPublisher {

    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(String message) {
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        // 发布事件
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
