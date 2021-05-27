package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.OrderMapper;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.entity.Order;
import com.jourwon.spring.boot.model.query.OrderQuery;
import com.jourwon.spring.boot.service.OrderService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.apache.commons.lang3.StringUtils;
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
            IPage<Order> pageInfo = this.baseMapper.selectPage(
                    new Page<>(orderQuery.getPageNum(), orderQuery.getPageSize(), false),
                    Wrappers.<Order>lambdaQuery()
                            .between(StringUtils.isNotBlank(orderQuery.getPaymentDateTimeStart()), Order::getPaymentTime, orderQuery.getPaymentDateTimeStart(), orderQuery.getPaymentDateTimeEnd())
                            .eq(null != orderQuery.getOrderStatus(), Order::getOrderStatus, orderQuery.getOrderStatus()));
            return BeanTransformUtils.transformList(pageInfo.getRecords(), OrderDTO.class);
        } else if (isCurrentPage == 1) {
            List<Order> list = this.list(Wrappers.<Order>lambdaQuery()
                    .between(StringUtils.isNotBlank(orderQuery.getPaymentDateTimeStart()), Order::getPaymentTime, orderQuery.getPaymentDateTimeStart(), orderQuery.getPaymentDateTimeEnd())
                    .eq(null != orderQuery.getOrderStatus(), Order::getOrderStatus, orderQuery.getOrderStatus()));
            return BeanTransformUtils.transformList(list, OrderDTO.class);
        } else {
            throw new IllegalArgumentException("是否为当前页参数非法");
        }
    }

    @Override
    public void insertBatchOrder(List<OrderDTO> list) {
        List<Order> orderList = BeanTransformUtils.transformList(list, Order.class);
        this.saveBatch(orderList);
    }

}
