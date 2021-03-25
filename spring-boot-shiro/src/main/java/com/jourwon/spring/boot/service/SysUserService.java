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

    SysUserDO getByUsername(String username);

}
