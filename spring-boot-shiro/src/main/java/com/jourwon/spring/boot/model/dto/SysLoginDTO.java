package com.jourwon.spring.boot.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JourWon
 * @date 2021/3/25
 */
@Data
@ApiModel(value = "SysLoginDTO-登录入参")
public class SysLoginDTO implements Serializable {

    private static final long serialVersionUID = 8239607067636018259L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
