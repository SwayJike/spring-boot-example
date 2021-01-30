package com.jourwon.spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通知消息
 *
 * @author JourWon
 * @date 2021/1/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeMessage implements Serializable {

    private static final long serialVersionUID = -4901686800092007154L;

    private String name;

    private String value;

}
