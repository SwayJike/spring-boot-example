package com.jourwon.spring.boot.listener;

import com.jourwon.spring.boot.event.CustomApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 注解事件监听器
 *
 * @author JourWon
 * @date 2021/11/28
 */
@Slf4j
@Component
public class AnnotationCustomApplicationListener {

    @EventListener(CustomApplicationEvent.class)
    public void listener(CustomApplicationEvent customApplicationEvent) {
        log.info("EventListener注解方式接收到的消息为:{}", customApplicationEvent.getMessage());
    }

}
