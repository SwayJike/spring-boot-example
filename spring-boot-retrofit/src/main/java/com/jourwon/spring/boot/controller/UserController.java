package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.HttpApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    private HttpApi httpApi;

    @GetMapping("/{userId}")
    @ApiOperation("通过主键获取用户")
    public UserVO getById(@PathVariable("userId") Long userId) {
        return httpApi.getById(userId);
    }

    @GetMapping
    @ApiOperation("查询所有的用户列表")
    public List<UserVO> list() {
        return httpApi.list();
    }

    @GetMapping("/page1")
    @ApiOperation("分页查询用户1")
    public CommonPageVO<UserVO> page1(@Valid UserQuery userQuery) {
        return httpApi.page1(userQuery.getPageNum(), userQuery.getPageSize(), userQuery.getUsername(), userQuery.getEmail(), userQuery.getMobilePhoneNumber());
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("根据主键删除用户1")
    public boolean removeById(@PathVariable("userId") Long userId) {
        return httpApi.removeById(userId);
    }

    @PostMapping
    @ApiOperation("新增用户1")
    public boolean insertSelective(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        return httpApi.insert(insertUserDTO);
    }

    @PutMapping("/{userId}")
    @ApiOperation("根据主键更新用户1")
    public boolean updateByPrimaryKeySelective(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        return httpApi.updateUser(userId, updateUserDTO);
    }

}
