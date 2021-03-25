package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.entity.SysUserDO;
import com.jourwon.spring.boot.util.ShiroUtils;

/**
 * Controller公共组件
 *
 * @author JourWon
 * @date 2021/3/25
 */
public abstract class AbstractController {

    protected SysUserDO getUser() {
        return ShiroUtils.getUser();
    }

    protected Long getUserId() {
        return getUser().getId();
    }

}
