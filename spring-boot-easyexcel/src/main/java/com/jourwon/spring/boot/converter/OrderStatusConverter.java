package com.jourwon.spring.boot.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.jourwon.spring.boot.enums.OrderStatusEnum;

import java.util.Objects;

/**
 * EasyExcel 订单状态转换器
 *
 * @author JourWon
 * @date 2021/5/22
 */
public class OrderStatusConverter implements Converter<Integer> {

    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String description = cellData.toString();
        return OrderStatusEnum.getOrderStatusByDescription(description);
    }

    @Override
    public CellData convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(Objects.requireNonNull(OrderStatusEnum.getDescriptionByOrderStatus(value)));
    }

}
