package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.dto.UserDTO;

/**
 * 用户service接口
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserService {

    /**
     * 通过主键获取用户
     *
     * @param userId 用户id
     * @return UserDTO
     */
    UserDTO getByPrimaryKey(Long userId);

}
