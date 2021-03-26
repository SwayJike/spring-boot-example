package com.jourwon.spring.boot.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_captcha")
public class SysCaptchaDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;


}
