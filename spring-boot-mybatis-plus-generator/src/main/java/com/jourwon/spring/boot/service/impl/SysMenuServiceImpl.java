package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.model.entity.SysMenuDO;
import com.jourwon.spring.boot.mapper.SysMenuMapper;
import com.jourwon.spring.boot.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuDO> implements SysMenuService {

}
