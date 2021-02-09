package com.jourwon.spring.boot.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * bean转换工具类
 *
 * @author JourWon
 * @date 2021/2/6
 */
public class BeanTransformUtils {

    public static <T> T transform(Object source, Class<T> targetClass) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(targetClass, "Target class must not be null");
        T targetObj = null;
        try {
            targetObj = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert targetObj != null;
        BeanUtils.copyProperties(source, targetObj);
        return targetObj;
    }

    public static <S, T> List<T> transformList(List<S> source, Class<T> targetClass) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(targetClass, "Target class must not be null");
        List<T> targetList = new ArrayList<>();
        for (S sourceObj : source) {
            T targetObj = null;
            try {
                targetObj = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            assert targetObj != null;
            BeanUtils.copyProperties(sourceObj, targetObj);
            targetList.add(targetObj);
        }
        return targetList;
    }

}
