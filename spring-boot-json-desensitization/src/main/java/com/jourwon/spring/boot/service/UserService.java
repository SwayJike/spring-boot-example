package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.vo.UserVO;

import java.util.List;

/**
 * 用户表 服务类
 *
 * @author JourWon
 * @date 2021-02-10
 */
public interface UserService {

    /**
     * 查询所有的用户列表1
     *
     * @return List<UserVO>
     */
    List<UserVO> list();

}
