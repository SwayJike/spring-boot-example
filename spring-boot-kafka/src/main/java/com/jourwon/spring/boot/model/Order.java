package com.jourwon.spring.boot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单实体类
 *
 * @author JourWon
 * @date 2021/4/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -153049845060952694L;
    /**
     * 订单id
     */
    private long orderId;
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

}
