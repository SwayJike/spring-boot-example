package com.jourwon.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型类型枚举，sheet的模型类型
 *
 * @author JourWon
 * @date 2021/5/28
 */
@Getter
@AllArgsConstructor
public enum ModelTypeEnum {

    /**
     * 订单数据1
     */
    ORDER_FIRST(0, "订单数据1"),

    /**
     * 订单数据2
     */
    ORDER_SECOND(1, "订单数据2"),
    ;

    private final int sheetNum;

    private final String sheetName;
}
