package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.jourwon.spring.boot.mapper.SysCaptchaMapper;
import com.jourwon.spring.boot.model.entity.SysCaptcha;
import com.jourwon.spring.boot.service.SysCaptchaService;
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
    private Producer producer;

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
        // 1分钟后过期
        sysCaptcha.setExpireTime(LocalDateTime.now().plusMinutes(1));
        this.save(sysCaptcha);

        return producer.createImage(captcha);
    }

    @Override
    public boolean validation(String uuid, String captcha) {
        SysCaptcha sysCaptcha = this.getOne(new QueryWrapper<SysCaptcha>().eq("uuid", uuid));
        if (sysCaptcha == null) {
            return false;
        }

        //删除验证码
        this.removeById(uuid);

        return sysCaptcha.getCaptcha().equalsIgnoreCase(captcha) && sysCaptcha.getExpireTime().toInstant(ZoneOffset.of("+8")).toEpochMilli() >= System.currentTimeMillis();
    }

}
