package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.entity.SysUserRole;
import com.jourwon.spring.boot.mapper.SysUserRoleMapper;
import com.jourwon.spring.boot.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关联关系 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-03-24
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
