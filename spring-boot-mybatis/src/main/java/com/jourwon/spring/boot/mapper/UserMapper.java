package com.jourwon.spring.boot.mapper;

import com.jourwon.spring.boot.entity.User;

import java.util.List;

/**
 * 用户mapper
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserMapper {

    /**
     * 通过主键获取用户
     *
     * @param userId
     * @return
     */
    User getByPrimaryKey(Long userId);

    /**
     * 查询所有的用户列表
     *
     * @return
     */
    List<User> listUsers();

    /**
     * 根据主键删除用户
     *
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 可选择的新增用户
     *
     * @param user
     * @return
     */
    int insertSelective(User user);

    /**
     *
     * 根据主键可选择的更新用户
     *
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 根据主键更新用户
     *
     * @param user
     * @return
     */
    int updateByPrimaryKey(User user);

}
