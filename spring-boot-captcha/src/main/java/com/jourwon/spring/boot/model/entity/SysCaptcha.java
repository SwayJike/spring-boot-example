package com.jourwon.spring.boot.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 验证码
 *
 * @author JourWon
 * @date 2021/2/19
 */
@Data
@TableName("sys_captcha")
@ApiModel("SysCaptcha-验证码")
public class SysCaptcha {

    @ApiModelProperty("uuid")
    @TableId(type = IdType.INPUT)
    private String uuid;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

}
