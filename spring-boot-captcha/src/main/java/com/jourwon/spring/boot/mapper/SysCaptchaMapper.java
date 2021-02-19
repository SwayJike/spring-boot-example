package com.jourwon.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jourwon.spring.boot.model.entity.SysCaptcha;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码mapper
 *
 * @author JourWon
 * @date 2021/2/19
 */
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptcha> {
}
