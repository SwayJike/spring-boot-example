package com.jourwon.spring.boot.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 新增更新用户Query
 *
 * @author JourWon
 * @date 2021/2/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "InsertUpdateUserQuery-新增更新用户Query")
public class InsertUpdateUserQuery implements Serializable {

    private static final long serialVersionUID = 7178469188100767131L;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码")
    private String mobilePhoneNumber;

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "删除状态(0:未删除,1:已删除)")
    private Short deleteState;

}
