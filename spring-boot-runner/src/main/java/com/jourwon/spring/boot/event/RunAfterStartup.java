package com.jourwon.spring.boot.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Spring Boot中的应用事件
 *
 * @author JourWon
 * @date 2021/12/4
 */
@Slf4j
@Component
public class RunAfterStartup {

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        log.info("Spring Boot中的应用事件,Yaaah, I am running........");
    }

}
