package com.jourwon.spring.boot.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.jourwon.spring.boot.converter.LocalDateTimeConverter;
import com.jourwon.spring.boot.converter.OrderStatusConverter;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单DTO
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Data
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 4405596659740548404L;

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "订单号", order = 1)
    private String orderId;

    @NumberFormat
    @ExcelProperty(value = "金额", order = 2)
    private BigDecimal amount;

    @ExcelProperty(value = "支付时间", order = 3, converter = LocalDateTimeConverter.class)
    private LocalDateTime paymentTime;

    @ExcelProperty(value = "订单状态", order = 4, converter = OrderStatusConverter.class)
    private Integer orderStatus;

}
