package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.mapper.demo.UserDemoMapper;
import com.jourwon.spring.boot.mapper.test.UserTestMapper;
import com.jourwon.spring.boot.model.dto.UserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户service实现类
 *
 * @author JourWon
 * @date 2021/2/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDemoMapper userDemoMapper;

    @Resource
    private UserTestMapper userTestMapper;

    @Override
    public List<UserDTO> listTestUsers() {
        List<User> listUsers = userTestMapper.listUsers();
        return BeanTransformUtils.transformList(listUsers, UserDTO.class);
    }

    @Override
    public List<UserDTO> listDemoUsers() {
        List<User> listUsers = userDemoMapper.listUsers();
        return BeanTransformUtils.transformList(listUsers, UserDTO.class);
    }

}
