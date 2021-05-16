package com.jourwon.spring.boot.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Data
@Builder
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -2184794081121324925L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 订单状态,0:处理中,1:支付成功,2:支付失败
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
