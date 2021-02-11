package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表 前端控制器
 *
 * @author JourWon
 * @date 2021-02-10
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list1")
    @ApiOperation("查询所有的用户列表1")
    public List<UserVO> list1() {
        return userService.list1();
    }

    @GetMapping("/list2")
    @ApiOperation("查询所有的用户列表2")
    public List<UserVO> list2() {
        return userService.list2();
    }

}
