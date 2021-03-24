package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.entity.SysMenu;
import com.jourwon.spring.boot.mapper.SysMenuMapper;
import com.jourwon.spring.boot.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-03-24
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

}
