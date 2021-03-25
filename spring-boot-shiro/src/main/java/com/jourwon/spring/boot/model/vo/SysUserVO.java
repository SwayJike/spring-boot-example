package com.jourwon.spring.boot.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JourWon
 * @date 2021/3/25
 */
@Data
@ApiModel(value = "CommonResponse-统一返回前端的响应对象")
public class SysUserVO implements Serializable {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobilePhoneNumber;

    @ApiModelProperty("锁定状态，0-正常，1-禁用")
    private Integer lockStatus;

}
