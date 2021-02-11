package com.jourwon.spring.boot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserVO> list1() {
        List<User> users = userMapper.selectList(null);
        return BeanTransformUtils.transformList(users, UserVO.class);
    }

    @Override
    @DS("slave")
    public List<UserVO> list2() {
        List<User> users = userMapper.selectList(null);
        return BeanTransformUtils.transformList(users, UserVO.class);
    }
}
