package com.jourwon.spring.boot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 新增用户DTO
 *
 * @author JourWon
 * @date 2021/2/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "InsertUserDTO-新增用户DTO")
public class InsertUserDTO implements Serializable {

    private static final long serialVersionUID = 7178469188100767131L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号码")
    private String mobilePhoneNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "删除状态(0:未删除,1:已删除)")
    private Short deleteState;

}
