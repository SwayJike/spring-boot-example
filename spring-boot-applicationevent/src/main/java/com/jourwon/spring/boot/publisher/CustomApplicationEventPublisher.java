package com.jourwon.spring.boot.publisher;

import com.jourwon.spring.boot.event.CustomApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 创建事件的发布者 注入ApplicationEventPublisher
 *
 * @author JourWon
 * @date 2021/11/27
 */
@Slf4j
@Component
public class CustomApplicationEventPublisher {

    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(String message) {
        log.info("开始发布自定义事件");
        CustomApplicationEvent customApplicationEvent = new CustomApplicationEvent(this, message);
        // 发布事件
        applicationEventPublisher.publishEvent(customApplicationEvent);
        log.info("发布自定义事件结束");
    }

}
