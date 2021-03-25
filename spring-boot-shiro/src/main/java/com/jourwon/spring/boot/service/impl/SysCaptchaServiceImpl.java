package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.SysCaptchaMapper;
import com.jourwon.spring.boot.model.entity.SysCaptchaDO;
import com.jourwon.spring.boot.service.SysCaptchaService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统验证码 服务实现类
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptchaDO> implements SysCaptchaService {

}
