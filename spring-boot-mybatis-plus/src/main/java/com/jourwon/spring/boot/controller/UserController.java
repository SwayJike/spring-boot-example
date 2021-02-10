package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @GetMapping("/{userId}")
    @ApiOperation("通过主键获取用户")
    public UserVO getById(@PathVariable("userId") Long userId) {
        User user = userService.getById(userId);
        return BeanTransformUtils.transform(user, UserVO.class);
    }

    @GetMapping
    @ApiOperation("查询所有的用户列表")
    public List<UserVO> list() {
        List<User> list = userService.list();
        return BeanTransformUtils.transformList(list, UserVO.class);
    }

    @GetMapping("/page1")
    @ApiOperation("分页查询用户1")
    public CommonPageVO<UserVO> page1(@Valid UserQuery userQuery) {
        return userService.page1(userQuery);
    }

    @GetMapping("/page2")
    @ApiOperation("分页查询用户2")
    public CommonPageVO<UserVO> page2(@Valid UserQuery userQuery) {
        return userService.page2(userQuery);
    }

    @GetMapping("/page3")
    @ApiOperation("分页查询用户3")
    public CommonPageVO<UserVO> page3(@Valid UserQuery userQuery) {
        return userService.page3(userQuery);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("根据主键删除用户1")
    public boolean removeById(@PathVariable("userId") Long userId) {
        return userService.removeById(userId);
    }

    @DeleteMapping("/removeUser/{userId}")
    @ApiOperation("根据主键删除用户2")
    public boolean removeUser(@PathVariable("userId") Long userId) {
        return userService.removeUser(userId);
    }

    @PostMapping
    @ApiOperation("新增用户1")
    public boolean insertSelective(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        User user = BeanTransformUtils.transform(insertUserDTO, User.class);
        boolean b = userService.saveOrUpdate(user);
        System.out.println("用户id:" + user.getUserId());
        return b;
    }

    @PostMapping("/saveUser")
    @ApiOperation("新增用户2")
    public boolean saveUser(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        return userService.saveUser(insertUserDTO);
    }

    @PutMapping("/{userId}")
    @ApiOperation("根据主键更新用户1")
    public boolean updateByPrimaryKeySelective(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        updateUserDTO.setUserId(userId);
        User user = BeanTransformUtils.transform(updateUserDTO, User.class);
        return userService.saveOrUpdate(user);
    }

    @PutMapping("/updateUser/{userId}")
    @ApiOperation("根据主键更新用户2")
    public boolean updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        updateUserDTO.setUserId(userId);
        return userService.updateUser(updateUserDTO);
    }

}
