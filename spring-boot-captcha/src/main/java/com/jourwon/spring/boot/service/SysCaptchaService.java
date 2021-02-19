package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.entity.SysCaptcha;

import java.awt.image.BufferedImage;

/**
 * 验证码service
 *
 * @author JourWon
 * @date 2021/2/19
 */
public interface SysCaptchaService extends IService<SysCaptcha> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     *
     * @param uuid    uuid
     * @param captcha 验证码
     * @return true：成功  false：失败
     */
    boolean validation(String uuid, String captcha);

}
