package com.jourwon.spring.boot.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户Token
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_token")
public class SysUserTokenDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;


}
