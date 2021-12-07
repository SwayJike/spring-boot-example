package com.jourwon.spring.boot.listener;

import com.jourwon.spring.boot.event.CustomApplicationEvent;
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
public class CustomApplicationListener implements ApplicationListener<CustomApplicationEvent> {

    @Override
    public void onApplicationEvent(CustomApplicationEvent event) {
        log.info("onApplicationEvent方法接收到的消息:{}", event.getMessage());
    }

}
