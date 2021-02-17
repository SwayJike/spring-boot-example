package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.UserDTO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("/listTestUsers")
    @ApiOperation("查询所有的用户列表1-test")
    public List<UserVO> listTestUsers() {
        List<UserDTO> listUsers = userService.listTestUsers();
        return BeanTransformUtils.transformList(listUsers, UserVO.class);
    }

    @GetMapping("/listDemoUsers")
    @ApiOperation("查询所有的用户列表2-demo")
    public List<UserVO> listDemoUsers() {
        List<UserDTO> listUsers = userService.listDemoUsers();
        return BeanTransformUtils.transformList(listUsers, UserVO.class);
    }

}
