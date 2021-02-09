package com.jourwon.spring.boot.mapper;

import com.jourwon.spring.boot.model.entity.User;

import java.util.List;

/**
 * 用户mapper
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserMapper {

    /**
     * 分页查询用户
     *
     * @param user 用户
     * @return List<User> 用户列表
     */
    List<User> page(User user);

}
