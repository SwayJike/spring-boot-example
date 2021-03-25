package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.SysUserMapper;
import com.jourwon.spring.boot.model.entity.SysUserDO;
import com.jourwon.spring.boot.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService {

    @Override
    public SysUserDO getByUsername(String username) {
        return this.baseMapper.selectOne(Wrappers.<SysUserDO>lambdaQuery().eq(SysUserDO::getUsername, username));
    }
}
