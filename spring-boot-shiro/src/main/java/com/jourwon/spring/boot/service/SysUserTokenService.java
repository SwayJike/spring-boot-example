package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.dto.SysUserTokenDTO;
import com.jourwon.spring.boot.model.entity.SysUserTokenDO;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
public interface SysUserTokenService extends IService<SysUserTokenDO> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    SysUserTokenDTO createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);

}
