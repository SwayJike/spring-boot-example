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
 * 新增用户请求参数
 *
 * @author JourWon
 * @date 2021/1/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserQuery-新增用户请求参数")
public class UserQuery implements Serializable {

    private static final long serialVersionUID = -5131037261093211884L;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码")
    private String mobilePhoneNumber;

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

}
