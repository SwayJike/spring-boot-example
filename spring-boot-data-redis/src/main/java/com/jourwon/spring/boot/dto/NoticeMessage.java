package com.jourwon.spring.boot.dto;

import java.io.Serializable;

/**
 * 通知消息
 *
 * @author JourWon
 * @date 2021/1/18
 */
public class NoticeMessage implements Serializable {

    private static final long serialVersionUID = -4901686800092007154L;

    private String name;

    private String value;

    public NoticeMessage() {
    }

    public NoticeMessage(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NoticeMessage{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
