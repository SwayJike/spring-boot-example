package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.SysUserTokenMapper;
import com.jourwon.spring.boot.model.dto.SysUserTokenDTO;
import com.jourwon.spring.boot.model.entity.SysUserTokenDO;
import com.jourwon.spring.boot.service.SysUserTokenService;
import com.jourwon.spring.boot.shiro.TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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

    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;


    @Override
    public SysUserTokenDTO createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //过期时间
        long milli = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() + EXPIRE * 1000;
        LocalDateTime expireTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault());
        //判断是否生成过token
        SysUserTokenDO sysUserToken = this.getById(userId);
        if (sysUserToken == null) {
            sysUserToken = new SysUserTokenDO();
            sysUserToken.setUserId(userId);
            sysUserToken.setToken(token);
            sysUserToken.setExpireTime(expireTime);

            //保存token
            this.save(sysUserToken);
        } else {
            sysUserToken.setToken(token);
            sysUserToken.setExpireTime(expireTime);

            //更新token
            this.updateById(sysUserToken);
        }

        return new SysUserTokenDTO(token, EXPIRE);
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserTokenDO tokenEntity = new SysUserTokenDO();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }

}
