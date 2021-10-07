package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.dto.UserDTO;
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

    @GetMapping("/page")
    @ApiOperation("分页查询用户")
    public CommonPageVO<UserVO> page(@Valid UserQuery userQuery) {
        return userService.page(userQuery);
    }

    @GetMapping("/pageBySpecification")
    @ApiOperation("分页查询用户(根据特定条件)")
    public CommonPageVO<UserVO> pageBySpecification(@Valid UserQuery userQuery) {
        return userService.pageBySpecification(userQuery);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("根据主键删除用户")
    public boolean deleteByPrimaryKey(@PathVariable("userId") Long userId) {
        return userService.deleteByPrimaryKey(userId);
    }

    @PostMapping
    @ApiOperation("新增用户")
    public boolean insert(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        return userService.insert(insertUserDTO);
    }

    @PutMapping("/{userId}")
    @ApiOperation("根据主键更新用户")
    public boolean updateByPrimaryKey(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        updateUserDTO.setUserId(userId);
        return userService.updateByPrimaryKey(updateUserDTO);
    }

    @GetMapping("/findByUsername")
    @ApiOperation("根据用户名查询用户")
    public UserVO findByUsername(@RequestParam String username) {
        UserDTO userDTO = userService.findByUsername(username);
        return BeanTransformUtils.transform(userDTO, UserVO.class);
    }

    @GetMapping("/countByUsername")
    @ApiOperation("统计用户名的数量")
    public Long countByUsername(@RequestParam String username) {
        return userService.countByUsername(username);
    }

    @GetMapping("/findByEmailLike")
    @ApiOperation("根据邮箱模糊查询用户")
    public List<UserVO> findByEmailLike(@RequestParam String email) {
        List<UserDTO> dtoList = userService.findByEmailLike(email);
        return BeanTransformUtils.transformList(dtoList, UserVO.class);
    }

    @GetMapping("/updateByUsernameAndUserId")
    @ApiOperation("根据用户名更新用户")
    public Integer updateByUsernameAndUserId(@RequestParam String username, @RequestParam Long userId) {
        return userService.updateByUsernameAndUserId(username, userId);
    }

    @GetMapping("/findByEmail")
    @ApiOperation("根据邮箱查询用户")
    public UserVO findByEmail(@RequestParam String email) {
        UserDTO userDTO = userService.findByEmail(email);
        return BeanTransformUtils.transform(userDTO, UserVO.class);
    }

}
