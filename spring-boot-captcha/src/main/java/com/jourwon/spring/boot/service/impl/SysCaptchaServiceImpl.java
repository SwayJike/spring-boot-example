package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.jourwon.spring.boot.mapper.SysCaptchaMapper;
import com.jourwon.spring.boot.model.entity.SysCaptcha;
import com.jourwon.spring.boot.service.SysCaptchaService;
import com.jourwon.spring.boot.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author JourWon
 * @date 2021/2/19
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements SysCaptchaService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private Producer producer;

    private static final String CAPTCHA_KEY_PREFIX = "jourwon:captcha:";

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new RuntimeException("uuid不能为空");
        }
        // 生成文字验证码
        String captcha = producer.createText();

        SysCaptcha sysCaptcha = new SysCaptcha();
        sysCaptcha.setUuid(uuid);
        sysCaptcha.setCaptcha(captcha);
        // 3分钟后过期
        sysCaptcha.setExpireTime(LocalDateTime.now().plusMinutes(3));

        // 方式一：将验证码保存到数据库
        // this.save(sysCaptcha);
        // 方式二：将验证码保存到redis
        redisUtils.set(CAPTCHA_KEY_PREFIX + uuid, sysCaptcha, 180L);

        return producer.createImage(captcha);
    }

    @Override
    public boolean validate(String uuid, String captcha) {
        return validateFromRedis(uuid, captcha);
    }

    private boolean validateFromMysql(String uuid, String captcha) {
        // 方式一：从MySQL数据库获取验证码
        SysCaptcha sysCaptcha = this.getOne(Wrappers.<SysCaptcha>lambdaQuery().eq(SysCaptcha::getUuid, uuid));
        if (sysCaptcha == null) {
            return false;
        }

        // 删除验证码
        this.removeById(uuid);
        return sysCaptcha.getCaptcha().equalsIgnoreCase(captcha) && sysCaptcha.getExpireTime().toInstant(ZoneOffset.of("+8")).toEpochMilli() >= System.currentTimeMillis();
    }

    private boolean validateFromRedis(String uuid, String captcha) {
        // 方式二：从Redis获取验证码
        SysCaptcha sysCaptcha = redisUtils.get(CAPTCHA_KEY_PREFIX + uuid, SysCaptcha.class);
        if (sysCaptcha == null || !sysCaptcha.getCaptcha().equalsIgnoreCase(captcha)) {
            return false;
        }

        redisUtils.delete(CAPTCHA_KEY_PREFIX + uuid);
        return true;
    }

}
