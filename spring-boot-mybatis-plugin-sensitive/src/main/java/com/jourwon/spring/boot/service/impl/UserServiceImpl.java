package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.mapper.UserMapper;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表 服务实现类
 *
 * @author JourWon
 * @date 2021-02-10
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserVO> list() {
        List<User> users = userMapper.listUsers();
        return BeanTransformUtils.transformList(users, UserVO.class);
    }

}
