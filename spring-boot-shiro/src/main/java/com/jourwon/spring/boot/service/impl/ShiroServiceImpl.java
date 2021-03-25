package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.constant.SysConstants;
import com.jourwon.spring.boot.mapper.SysMenuMapper;
import com.jourwon.spring.boot.mapper.SysUserMapper;
import com.jourwon.spring.boot.mapper.SysUserTokenMapper;
import com.jourwon.spring.boot.model.entity.SysMenuDO;
import com.jourwon.spring.boot.model.entity.SysUserDO;
import com.jourwon.spring.boot.model.entity.SysUserTokenDO;
import com.jourwon.spring.boot.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author JourWon
 * @date 2021/3/25
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == SysConstants.SUPER_ADMIN) {
            List<SysMenuDO> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuDO menu : menuList) {
                permsList.add(menu.getPermission());
            }
        } else {
            permsList = sysUserMapper.listPermissionByUserId(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenDO getByToken(String token) {
        return sysUserTokenMapper.getByToken(token);
    }

    @Override
    public SysUserDO getUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }

}
