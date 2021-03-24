package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.entity.SysRoleMenu;
import com.jourwon.spring.boot.mapper.SysRoleMenuMapper;
import com.jourwon.spring.boot.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单关联关系 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-03-24
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

}
