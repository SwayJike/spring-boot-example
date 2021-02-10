package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.UserDTO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
