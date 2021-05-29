package com.jourwon.spring.boot.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型解析监听器 -- 每解析一行会回调invoke()方法，整个excel解析结束会执行doAfterAllAnalysed()方法
 * 未完成:数据检验,返回错误信息
 * 导入excel出现错误处理的三种方式
 * 1、出现一条错误，立即回滚
 * 2、出现一条错误，正确的正常逻辑处理，出现错误时后面的不执行，并返回错误信息
 * 3、返回所有数据校验不通过的数据，正确的正常逻辑处理
 *
 * @author JourWon
 * @date 2021/5/27
 */
@Slf4j
public class ModelExcelListener<E> extends AnalysisEventListener<E> {

    private final List<E> list = new ArrayList<>();

    public ModelExcelListener() {

    }

    @Override
    public void invoke(E object, AnalysisContext context) {
        list.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("{}条数据解析完成!", list.size());
    }

    public List<E> getList() {
        return list;
    }

}
