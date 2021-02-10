package com.jourwon.spring.boot.mapper;

import com.jourwon.spring.boot.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 查询所有的用户列表
     *
     * @return List<User>
     */
    @Select("select user_id, username, password, mobile_phone_number, email, " +
            "delete_state, create_time, update_time from user")
    List<User> listUsers();

    /**
     * 根据主键删除用户
     *
     * @param userId 用户id
     * @return int
     */
    @Delete("delete from user\n" +
            "        where user_id = #{userId}")
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增用户
     *
     * @param user 用户
     * @return int
     */
    @Insert("insert into user (user_id, username, password,\n" +
            "          mobile_phone_number, email, delete_state,\n" +
            "          create_time, update_time)\n" +
            "        values (#{userId}, #{username}, #{password},\n" +
            "          #{mobilePhoneNumber}, #{email}, #{deleteState},\n" +
            "          #{createTime}, #{updateTime})")
    int insert(User user);

    /**
     * 根据主键更新用户
     *
     * @param user 用户
     * @return int
     */
    @Update("update user\n" +
            "        set username = #{username},\n" +
            "          password = #{password},\n" +
            "          mobile_phone_number = #{mobilePhoneNumber},\n" +
            "          email = #{email},\n" +
            "          delete_state = #{deleteState},\n" +
            "          create_time = #{createTime},\n" +
            "          update_time = #{updateTime}\n" +
            "        where user_id = #{userId}")
    int updateByPrimaryKey(User user);

}
