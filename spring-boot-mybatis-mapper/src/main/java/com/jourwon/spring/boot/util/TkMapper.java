package com.jourwon.spring.boot.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义mapper接口
 *
 * @author JourWon
 * @date 2021/2/10
 */
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
