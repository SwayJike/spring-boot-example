package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.entity.Order;
import com.jourwon.spring.boot.model.query.OrderQuery;

import java.util.List;

/**
 * @author JourWon
 * @date 2021/5/16
 */
public interface OrderService extends IService<Order> {

    List<OrderDTO> listOrder(OrderQuery orderQuery);

    void insertBatchOrder(List<OrderDTO> list);

}
