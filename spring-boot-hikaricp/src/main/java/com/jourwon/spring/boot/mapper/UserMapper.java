package com.jourwon.spring.boot.mapper;

import com.jourwon.spring.boot.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户mapper
 *
 * @author JourWon
 * @date 2021/2/5
 */
@Mapper
public interface UserMapper {

    /**
     * 通过主键获取用户
     *
     * @param userId 用户id
     * @return User
     */
    @Select("SELECT user_id, username, password, mobile_phone_number, email, " +
            "delete_state, create_time, update_time FROM user WHERE user_id = #{id}")
    User getByPrimaryKey(Long userId);

}
