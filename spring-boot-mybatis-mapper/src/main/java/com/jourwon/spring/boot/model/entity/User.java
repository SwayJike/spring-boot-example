package com.jourwon.spring.boot.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author JourWon
 * @date 2021/1/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User-用户")
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 5949235389416637695L;

    @Id
    @ApiModelProperty(value = "用户id")
    private Long userId;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
