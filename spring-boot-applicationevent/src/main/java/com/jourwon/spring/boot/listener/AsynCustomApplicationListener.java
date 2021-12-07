package com.jourwon.spring.boot.listener;

import com.jourwon.spring.boot.event.CustomApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步事件监听器
 *
 * @author JourWon
 * @date 2021/11/28
 */
@Slf4j
@Component
public class AsynCustomApplicationListener {

    @Async
    @EventListener(CustomApplicationEvent.class)
    public void asyncListener(CustomApplicationEvent customApplicationEvent) {
        log.info("异步事件监听,当前线程:{},消息为:{}", Thread.currentThread().getName(), customApplicationEvent.getMessage());
    }

}
