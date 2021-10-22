package com.jourwon.spring.boot.factory;

import java.util.List;

/**
 * 水果接口
 *
 * @author JourWon
 * @date 2021/10/23
 */
public interface Fruit<T> {

    /**
     * 获取水果类型
     *
     * @return Integer 水果类型
     */
    Integer getType();


    /**
     * 获取指定数量水果
     *
     * @param number 水果数量
     * @return List<T> 指定数量水果
     */
    List<T> list(int number);

}
