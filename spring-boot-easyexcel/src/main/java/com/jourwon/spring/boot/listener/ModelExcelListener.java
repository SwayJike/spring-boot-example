package com.jourwon.spring.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jourwon.spring.boot.enums.ModelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型解析监听器 -- 每解析一行会回调invoke()方法，整个excel解析结束会执行doAfterAllAnalysed()方法
 *
 * @author JourWon
 * @date 2021/5/27
 */
@Slf4j
public class ModelExcelListener<E> extends AnalysisEventListener<E> {

    private final List<E> list = new ArrayList<>();

    private final ModelTypeEnum modelTypeEnum;

    public ModelExcelListener(ModelTypeEnum modelTypeEnum) {
        this.modelTypeEnum = modelTypeEnum;
    }

    @Override
    public void invoke(E object, AnalysisContext context) {
        list.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("{}-{}条数据解析完成!", modelTypeEnum.getSheetName(), list.size());
    }

    public List<E> getList() {
        return list;
    }

}
