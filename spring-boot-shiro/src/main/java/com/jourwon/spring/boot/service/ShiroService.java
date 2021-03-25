package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.entity.SysUserDO;
import com.jourwon.spring.boot.model.entity.SysUserTokenDO;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author JourWon
 * @date 2021/3/25
 */
public interface ShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenDO getByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserDO getUser(Long userId);

}
