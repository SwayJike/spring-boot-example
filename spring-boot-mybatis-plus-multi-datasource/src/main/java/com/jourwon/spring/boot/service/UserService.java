package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.vo.UserVO;

import java.util.List;

/**
 * 用户表 服务类
 *
 * @author JourWon
 * @date 2021-02-10
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有的用户列表1
     *
     * @return List<UserVO>
     */
    List<UserVO> list1();

    /**
     * 查询所有的用户列表2
     *
     * @return List<UserVO>
     */
    List<UserVO> list2();

}
