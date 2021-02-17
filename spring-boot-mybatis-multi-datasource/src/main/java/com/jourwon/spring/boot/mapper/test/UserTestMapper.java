package com.jourwon.spring.boot.mapper.test;

import com.jourwon.spring.boot.model.entity.User;

import java.util.List;

/**
 * 用户mapper
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserTestMapper {

    /**
     * 查询所有的用户列表
     *
     * @return List<User>
     */
    // @Select("select user_id, username, password, mobile_phone_number, email, " +
    //         "delete_state, create_time, update_time from user")
    List<User> listUsers();

}
