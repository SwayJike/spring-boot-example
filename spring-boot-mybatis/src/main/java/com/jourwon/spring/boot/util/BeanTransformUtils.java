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
        Object targetObj = null;
        try {
            targetObj = targetClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, targetObj);
        return (T) targetObj;
    }

    public static <S, T> List<T> transformList(List<S> source, Class<T> targetClass) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(targetClass, "Target class must not be null");
        List<Object> targetList = new ArrayList<>();
        for (S sourceObj : source) {
            Object targetObj = null;
            try {
                targetObj = targetClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(sourceObj, targetObj);
            targetList.add(targetObj);
        }
        return (List<T>) targetList;
    }

}
