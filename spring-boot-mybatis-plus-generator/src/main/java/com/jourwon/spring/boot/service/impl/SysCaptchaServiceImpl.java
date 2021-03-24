package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.model.entity.SysCaptcha;
import com.jourwon.spring.boot.mapper.SysCaptchaMapper;
import com.jourwon.spring.boot.service.SysCaptchaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统验证码 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-03-25
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements SysCaptchaService {

}
