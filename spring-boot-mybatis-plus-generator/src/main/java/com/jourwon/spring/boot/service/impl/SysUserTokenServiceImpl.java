package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.model.entity.SysUserTokenDO;
import com.jourwon.spring.boot.mapper.SysUserTokenMapper;
import com.jourwon.spring.boot.service.SysUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserTokenDO> implements SysUserTokenService {

}
