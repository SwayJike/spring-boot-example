package com.jourwon.spring.boot.listener;

import com.jourwon.spring.boot.event.CustomSpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步时间监听
 *
 * @author JourWon
 * @date 2021/11/28
 */
@Slf4j
@Component
public class CustomAsynSpringEventListener {

    // @EventListener(CustomSpringEvent.class)
    // public void listener(CustomSpringEvent customSpringEvent) {
    //     System.out.println("事件监听,消息为:" + customSpringEvent.getMessage());
    // }

    @Async
    @EventListener(CustomSpringEvent.class)
    public void listener(CustomSpringEvent customSpringEvent) {
        log.info("异步事件监听,当前线程:{},消息为:{}", Thread.currentThread().getName(), customSpringEvent.getMessage());
    }

}
