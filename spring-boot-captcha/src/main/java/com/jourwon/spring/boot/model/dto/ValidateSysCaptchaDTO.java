package com.jourwon.spring.boot.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 校验验证码dto
 *
 * @author JourWon
 * @date 2021/2/20
 */
@Data
@ApiModel("ValidateSysCaptchaDTO-校验验证码dto")
public class ValidateSysCaptchaDTO implements Serializable {

    private static final long serialVersionUID = -3370406019971746013L;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("uuid")
    private String uuid;

}
