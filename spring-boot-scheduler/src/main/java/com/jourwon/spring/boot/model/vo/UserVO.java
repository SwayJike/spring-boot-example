package com.jourwon.spring.boot.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class UserVO implements Serializable {

    private static final long serialVersionUID = -2490262010558991748L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

}
