package com.jourwon.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jourwon.spring.boot.model.entity.SysUserTokenDO;

/**
 * <p>
 * 系统用户Token Mapper 接口
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenDO> {

    SysUserTokenDO getByToken(String token);

}
