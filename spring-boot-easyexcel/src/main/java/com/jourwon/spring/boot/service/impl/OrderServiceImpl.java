package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jourwon.spring.boot.mapper.OrderMapper;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.entity.Order;
import com.jourwon.spring.boot.model.query.OrderQuery;
import com.jourwon.spring.boot.service.OrderService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单 服务实现类
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<OrderDTO> listOrder(OrderQuery orderQuery) {
        Integer isCurrentPage = orderQuery.getIsCurrentPage();

        if (isCurrentPage == 0) {
            PageInfo<Order> pageInfo = PageHelper.startPage(orderQuery.getPageNum(), orderQuery.getPageSize())
                    .doSelectPageInfo(() -> this.baseMapper.selectList(Wrappers.<Order>lambdaQuery()
                            // .select(Order::getOrderId, Order::getAmount, Order::getPaymentTime, Order::getOrderStatus)
                            .between(Order::getPaymentTime, orderQuery.getPaymentDateTimeStart(), orderQuery.getPaymentDateTimeEnd())
                            .eq(Order::getOrderStatus, orderQuery.getOrderStatus())));

            return BeanTransformUtils.transformList(pageInfo.getList(), OrderDTO.class);
        } else if (isCurrentPage == 1) {
            List<Order> list = this.list(Wrappers.<Order>lambdaQuery()
                    .select(Order::getOrderId, Order::getAmount, Order::getPaymentTime, Order::getOrderStatus)
                    .between(Order::getPaymentTime, orderQuery.getPaymentDateTimeStart(), orderQuery.getPaymentDateTimeEnd())
                    .eq(Order::getOrderStatus, orderQuery.getOrderStatus()));
            return BeanTransformUtils.transformList(list, OrderDTO.class);
        } else {
            throw new IllegalArgumentException("是否为当前页参数非法");
        }
    }

    @Override
    public void insertBatchOrder(List<OrderDTO> list) {

    }

}
