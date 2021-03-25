package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.SysRoleMenuMapper;
import com.jourwon.spring.boot.model.entity.SysRoleMenuDO;
import com.jourwon.spring.boot.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单关联关系 服务实现类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements SysRoleMenuService {

}
