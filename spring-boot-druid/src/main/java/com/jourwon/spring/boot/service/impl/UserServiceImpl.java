package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.mapper.UserMapper;
import com.jourwon.spring.boot.model.dto.UserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户service实现类
 *
 * @author JourWon
 * @date 2021/2/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDTO getByPrimaryKey(Long userId) {
        User user = userMapper.getByPrimaryKey(userId);
        if (null == user) {
            throw new RuntimeException("查不到userId为" + userId + "对应的用户");
        }

        return BeanTransformUtils.transform(user, UserDTO.class);
    }

}
