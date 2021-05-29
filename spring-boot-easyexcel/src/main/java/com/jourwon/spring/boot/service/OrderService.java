package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.entity.Order;
import com.jourwon.spring.boot.model.query.OrderQuery;

import java.util.List;

/**
 * 订单service
 *
 * @author JourWon
 * @date 2021/5/16
 */
public interface OrderService extends IService<Order> {

    /**
     * 查询订单列表
     *
     * @param orderQuery 查询对象
     * @return List<OrderDTO> 订单列表
     */
    List<OrderDTO> listOrder(OrderQuery orderQuery);

    /**
     * 批量插入订单数据
     *
     * @param list 数据列表
     */
    void insertBatchOrder(List<OrderDTO> list);

}
