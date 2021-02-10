package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.entity.User;
import com.jourwon.spring.boot.mapper.UserMapper;
import com.jourwon.spring.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author JourWon
 * @since 2021-02-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
