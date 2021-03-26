package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.model.dto.SysLoginDTO;
import com.jourwon.spring.boot.model.dto.SysUserTokenDTO;
import com.jourwon.spring.boot.model.entity.SysUserDO;
import com.jourwon.spring.boot.model.vo.CommonResponse;
import com.jourwon.spring.boot.service.SysUserService;
import com.jourwon.spring.boot.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关
 *
 * @author JourWon
 * @date 2021/3/25
 */
@RestController
@Api(tags = {"登录相关"})
public class SysLoginController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    @ApiOperation(value = "登录")
    public CommonResponse<SysUserTokenDTO> login(@RequestBody SysLoginDTO sysLoginDTO) {
        //用户信息
        SysUserDO user = sysUserService.getByUsername(sysLoginDTO.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(sysLoginDTO.getPassword(), user.getSalt()).toHex())) {
            return CommonResponse.failure(CommonResponseCodeEnum.ACCOUNT_OR_PASSWORD_INCORRECT);
        }

        //账号锁定
        if (user.getLockStatus() == 1) {
            return CommonResponse.failure(CommonResponseCodeEnum.ACCOUNT_LOCKED);
        }

        //生成token，并保存到数据库
        SysUserTokenDTO token = sysUserTokenService.createToken(user.getId());
        return CommonResponse.success(token);
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    @ApiOperation(value = "退出")
    public CommonResponse logout() {
        sysUserTokenService.logout(getUserId());
        return CommonResponse.success();
    }

}
