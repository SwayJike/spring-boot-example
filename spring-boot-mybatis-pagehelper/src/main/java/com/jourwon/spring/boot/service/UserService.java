package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;

/**
 * 用户service接口
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserService {

    /**
     * 分页查询用户
     *
     * @param userQuery 用户查询参数
     * @return CommonPageVO<UserVO> 分页数据结构
     */
    CommonPageVO<UserVO> page(UserQuery userQuery);

}
