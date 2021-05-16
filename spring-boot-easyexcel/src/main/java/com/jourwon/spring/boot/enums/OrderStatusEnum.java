package com.jourwon.spring.boot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 订单状态枚举类
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Getter
@RequiredArgsConstructor
public enum OrderStatusEnum {

    /**
     * 处理中
     */
    PROCESSING(0, "处理中"),

    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功"),

    /**
     * 支付失败
     */
    FAIL(2, "支付失败"),

    ;

    private final Integer orderStatus;

    private final String description;

    public static OrderStatusEnum fromOrderStatus(Integer orderStatus) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getOrderStatus().equals(orderStatus)) {
                return orderStatusEnum;
            }
        }
        throw new IllegalArgumentException("Unknown Order Status " + orderStatus);
    }

}
