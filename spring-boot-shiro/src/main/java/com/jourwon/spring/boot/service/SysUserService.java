package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.entity.SysUserDO;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
public interface SysUserService extends IService<SysUserDO> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    SysUserDO getByUsername(String username);

}
