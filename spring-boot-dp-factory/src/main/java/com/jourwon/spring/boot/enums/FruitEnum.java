package com.jourwon.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 水果枚举
 *
 * @author JourWon
 * @date 2021/10/23
 */
@Getter
@AllArgsConstructor
public enum FruitEnum {

    /**
     * 苹果
     */
    APPLE(1, "苹果"),

    /**
     * 香蕉
     */
    BANANA(2, "香蕉"),

    /**
     * 葡萄
     */
    GRAPE(3, "葡萄");

    private Integer type;

    private String description;

}
