package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户controller
 *
 * @author JourWon
 * @date 2021/2/6
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/page1")
    @ApiOperation("分页查询用户1")
    public CommonPageVO<UserVO> page1(@Valid UserQuery userQuery) {
        return userService.page(userQuery);
    }

    @GetMapping("/page2")
    @ApiOperation("分页查询用户2")
    public CommonPageVO<UserVO> page2(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize,
                                      @RequestParam(required = false, value = "usernmae") String usernmae, @RequestParam(required = false, value = "mobilePhoneNumber") String mobilePhoneNumber,
                                      @RequestParam(required = false, value = "email") String email) {
        UserQuery userQuery = UserQuery.builder().pageNum(pageNum).pageSize(pageSize).username(usernmae)
                .mobilePhoneNumber(mobilePhoneNumber).email(email).build();
        return userService.page(userQuery);
    }

}
