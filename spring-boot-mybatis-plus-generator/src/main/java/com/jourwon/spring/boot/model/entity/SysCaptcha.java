package com.jourwon.spring.boot.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author JourWon
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_captcha")
public class SysCaptcha implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;


}
