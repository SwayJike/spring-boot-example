package com.jourwon.spring.boot.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author JourWon
 * @since 2021-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysCaptcha对象", description="系统验证码")
public class SysCaptcha implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统验证码ID")
    private Long id;

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
