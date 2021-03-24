package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.entity.SysUser;
import com.jourwon.spring.boot.mapper.SysUserMapper;
import com.jourwon.spring.boot.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-03-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
