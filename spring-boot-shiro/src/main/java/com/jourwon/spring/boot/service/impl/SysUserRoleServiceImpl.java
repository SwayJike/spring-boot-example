package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.SysUserRoleMapper;
import com.jourwon.spring.boot.model.entity.SysUserRoleDO;
import com.jourwon.spring.boot.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关联关系 服务实现类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleDO> implements SysUserRoleService {

}
