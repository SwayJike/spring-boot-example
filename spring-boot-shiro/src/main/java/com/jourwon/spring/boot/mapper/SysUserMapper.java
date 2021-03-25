package com.jourwon.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jourwon.spring.boot.model.entity.SysUserDO;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
public interface SysUserMapper extends BaseMapper<SysUserDO> {

    List<String> listPermissionByUserId(long userId);

}
