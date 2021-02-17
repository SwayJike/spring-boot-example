package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.dto.UserDTO;

import java.util.List;

/**
 * 用户service接口
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserService {

    /**
     * 查询所有的用户列表
     *
     * @return List<UserDTO>
     */
    List<UserDTO> listTestUsers();

    /**
     * 查询所有的用户列表
     *
     * @return List<UserDTO>
     */
    List<UserDTO> listDemoUsers();

}
