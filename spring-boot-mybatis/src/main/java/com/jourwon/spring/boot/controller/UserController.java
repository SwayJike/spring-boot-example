package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.dto.UserDTO;
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

    @GetMapping("/{userId}")
    @ApiOperation("通过主键获取用户")
    public UserVO getByPrimaryKey(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.getByPrimaryKey(userId);
        return BeanTransformUtils.transform(userDTO, UserVO.class);
    }

    @GetMapping
    @ApiOperation("查询所有的用户列表")
    public List<UserVO> listUsers() {
        List<UserDTO> listUsers = userService.listUsers();
        return BeanTransformUtils.transformList(listUsers, UserVO.class);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("根据主键删除用户")
    public int deleteByPrimaryKey(@PathVariable("userId") Long userId) {
        return userService.deleteByPrimaryKey(userId);
    }

    @PostMapping
    @ApiOperation("新增用户")
    public int insert(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        return userService.insertSelective(insertUserDTO);
    }

    @PutMapping("/{userId}")
    @ApiOperation("根据主键更新用户")
    public int updateByPrimaryKey(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        updateUserDTO.setUserId(userId);
        return userService.updateByPrimaryKeySelective(updateUserDTO);
    }

}
