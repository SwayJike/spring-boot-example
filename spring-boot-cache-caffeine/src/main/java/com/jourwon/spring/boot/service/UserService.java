package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.entity.User;

/**
 * 用户表 服务类
 *
 * @author JourWon
 * @date 2021-02-10
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    User getByUserId(Long userId);

    /**
     * 新增用户
     *
     * @param insertUserDTO 新增用户DTO
     * @return boolean
     */
    boolean saveUser(InsertUserDTO insertUserDTO);

    /**
     * 更新用户
     *
     * @param updateUserDTO 更新用户DTO
     * @return boolean
     */
    boolean updateUser(UpdateUserDTO updateUserDTO);

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return boolean
     */
    boolean removeUser(Long userId);

}
