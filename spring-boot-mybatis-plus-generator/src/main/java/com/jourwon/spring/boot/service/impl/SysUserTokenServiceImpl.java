package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.entity.SysUserToken;
import com.jourwon.spring.boot.mapper.SysUserTokenMapper;
import com.jourwon.spring.boot.service.ISysUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-03-24
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements ISysUserTokenService {

}
