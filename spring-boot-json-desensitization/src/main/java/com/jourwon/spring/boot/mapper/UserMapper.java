package com.jourwon.spring.boot.mapper;

import com.jourwon.spring.boot.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户表 Mapper 接口
 *
 * @author JourWon
 * @date 2021-02-10
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有的用户列表
     *
     * @return List<User>
     */
    @Select("select user_id, username, password, mobile_phone_number, email, " +
            "delete_state, create_time, update_time from user")
    List<User> listUsers();

}
