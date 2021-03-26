package com.jourwon.spring.boot.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JourWon
 * @date 2021/3/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysUserTokenDTO-用户token")
public class SysUserTokenDTO implements Serializable {

    private static final long serialVersionUID = -622323528401278495L;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("过期时间,单位秒")
    private Integer expire;

}
