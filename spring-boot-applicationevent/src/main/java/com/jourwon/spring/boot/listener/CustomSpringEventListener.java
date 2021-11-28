package com.jourwon.spring.boot.listener;

import com.jourwon.spring.boot.event.CustomSpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 创建事件的监听者 实现ApplicationListener接口
 *
 * @author JourWon
 * @date 2021/11/27
 */
@Slf4j
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        log.info("接收到的事件:{}", event.getMessage());
    }

}
