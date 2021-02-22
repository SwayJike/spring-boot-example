package com.jourwon.spring.boot.dao;

import com.jourwon.spring.boot.entity.User;

/**
 * UserMapper
 *
 * @author JourWon
 * @date 2021/2/22
 */
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}