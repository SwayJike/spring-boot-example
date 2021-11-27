package com.jourwon.spring.boot.event;

import org.springframework.context.ApplicationEvent;

/**
 * 创建事件类 继承 ApplicationEvent
 *
 * @author JourWon
 * @date 2021/11/27
 */
public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
