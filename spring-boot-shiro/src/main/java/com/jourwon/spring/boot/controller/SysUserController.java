package com.jourwon.spring.boot.controller;


import com.jourwon.spring.boot.model.entity.SysUserDO;
import com.jourwon.spring.boot.model.vo.CommonResponse;
import com.jourwon.spring.boot.model.vo.SysUserVO;
import com.jourwon.spring.boot.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author JourWon
 * @date 2021/03/25
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = {"系统用户"})
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    @ApiOperation(value = "用户信息")
    public CommonResponse<SysUserVO> info(@PathVariable("userId") Long userId) {
        SysUserDO user = sysUserService.getById(userId);
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user, sysUserVO);

        return CommonResponse.success(sysUserVO);
    }

}
