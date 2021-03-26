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
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据token查询用户token
     *
     * @param token token
     * @return 用户token
     */
    SysUserTokenDO getByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId 用户id
     * @return 用户
     */
    SysUserDO getUser(Long userId);

}
