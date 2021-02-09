package com.jourwon.spring.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jourwon.spring.boot.mapper.UserMapper;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
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
    private UserMapper userMapper;

    @Override
    public CommonPageVO<UserVO> page(UserQuery userQuery) {
        User user = BeanTransformUtils.transform(userQuery, User.class);
        PageInfo<User> pageInfo = PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize())
                .doSelectPageInfo(() -> userMapper.page(user));

        List<UserVO> list = BeanTransformUtils.transformList(pageInfo.getList(), UserVO.class);
        return new CommonPageVO<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getSize(), pageInfo.getPages(), pageInfo.getTotal(), list);
    }

}
