package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jourwon.spring.boot.mapper.UserMapper;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import org.apache.commons.lang3.StringUtils;
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
    public CommonPageVO<UserVO> page1(UserQuery userQuery) {
        User user = BeanTransformUtils.transform(userQuery, User.class);
        PageInfo<User> pageInfo = PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize())
                .doSelectPageInfo(() -> userMapper.selectList(Wrappers.<User>lambdaQuery()
                        .likeRight(StringUtils.isNotBlank(userQuery.getUsername()), User::getUsername, userQuery.getUsername())
                        .likeRight(StringUtils.isNotBlank(userQuery.getMobilePhoneNumber()), User::getMobilePhoneNumber, userQuery.getMobilePhoneNumber())
                        .likeRight(StringUtils.isNotBlank(userQuery.getEmail()), User::getEmail, userQuery.getEmail())
                        .orderByDesc(User::getUserId)));

        List<UserVO> list = BeanTransformUtils.transformList(pageInfo.getList(), UserVO.class);
        CommonPageVO<UserVO> commonPageVO = new CommonPageVO<>(pageInfo.getPageNum(), pageInfo.getPageSize(),
                pageInfo.getSize(), pageInfo.getPages(), pageInfo.getTotal(), list);
        return commonPageVO;
    }

    @Override
    public CommonPageVO<UserVO> page2(UserQuery userQuery) {
        User user = BeanTransformUtils.transform(userQuery, User.class);
        IPage<User> page = new Page<>(userQuery.getPageNum(), userQuery.getPageSize());
        IPage<User> userPage = this.baseMapper.selectPage(page, Wrappers.<User>lambdaQuery()
        // IPage<User> userPage = userMapper.selectPage(page, Wrappers.<User>lambdaQuery()
                .likeRight(StringUtils.isNotBlank(userQuery.getUsername()), User::getUsername, userQuery.getUsername())
                .likeRight(StringUtils.isNotBlank(userQuery.getMobilePhoneNumber()), User::getMobilePhoneNumber, userQuery.getMobilePhoneNumber())
                .likeRight(StringUtils.isNotBlank(userQuery.getEmail()), User::getEmail, userQuery.getEmail())
                .orderByDesc(User::getUserId));
        List<UserVO> list = BeanTransformUtils.transformList(userPage.getRecords(), UserVO.class);
        CommonPageVO<UserVO> commonPageVO = new CommonPageVO<>((int) userPage.getCurrent(), (int) userPage.getSize(),
                userPage.getRecords().size(), (int) userPage.getPages(), userPage.getTotal(), list);
        return commonPageVO;
    }

    @Override
    public CommonPageVO<UserVO> page3(UserQuery userQuery) {
        User user = BeanTransformUtils.transform(userQuery, User.class);
        IPage<User> userPage = this.lambdaQuery()
                .likeRight(StringUtils.isNotBlank(userQuery.getUsername()), User::getUsername, userQuery.getUsername())
                .likeRight(StringUtils.isNotBlank(userQuery.getMobilePhoneNumber()), User::getMobilePhoneNumber, userQuery.getMobilePhoneNumber())
                .likeRight(StringUtils.isNotBlank(userQuery.getEmail()), User::getEmail, userQuery.getEmail())
                .orderByDesc(User::getUserId).page(new Page<>(userQuery.getPageNum(), userQuery.getPageSize()));

        List<UserVO> list = BeanTransformUtils.transformList(userPage.getRecords(), UserVO.class);
        CommonPageVO<UserVO> commonPageVO = new CommonPageVO<>((int) userPage.getCurrent(), (int) userPage.getSize(),
                userPage.getRecords().size(), (int) userPage.getPages(), userPage.getTotal(), list);
        return commonPageVO;
    }


    @Override
    public boolean saveUser(InsertUserDTO insertUserDTO) {
        User user = BeanTransformUtils.transform(insertUserDTO, User.class);

        // return userMapper.insert(user) >= 1;
        return this.save(user);
    }

    @Override
    public boolean updateUser(UpdateUserDTO updateUserDTO) {
        // int count = userMapper.update(null, Wrappers.<User>lambdaUpdate()
        //         .set(User::getUsername, updateUserDTO.getUsername())
        //         .set(User::getPassword,updateUserDTO.getPassword())
        //         .set(User::getMobilePhoneNumber,updateUserDTO.getMobilePhoneNumber())
        //         .set(User::getEmail,updateUserDTO.getEmail())
        //         .set(User::getDeleteState,updateUserDTO.getDeleteState())
        //         .eq(User::getUserId, updateUserDTO.getUserId()));
        // return count >= 1;

        // return this.lambdaUpdate()
        //         .set(User::getUsername, updateUserDTO.getUsername())
        //         .set(User::getPassword, updateUserDTO.getPassword())
        //         .set(User::getMobilePhoneNumber, updateUserDTO.getMobilePhoneNumber())
        //         .set(User::getEmail, updateUserDTO.getEmail())
        //         .set(User::getDeleteState, updateUserDTO.getDeleteState())
        //         .eq(User::getUserId, updateUserDTO.getUserId()).update();

        User user = BeanTransformUtils.transform(updateUserDTO, User.class);
        // return userMapper.updateById(user) >= 1;
        return this.updateById(user);
    }

    @Override
    public boolean removeUser(Long userId) {
        // return userMapper.deleteById(userId) >= 1;
        return this.removeById(userId);
    }

    @Override
    public List<UserVO> orderByDescMobilePhoneNumber() {
        List<User> list = userMapper.selectList(Wrappers.<User>lambdaQuery()
                .select(User::getUsername, User::getMobilePhoneNumber, User::getEmail)
                .groupBy(User::getMobilePhoneNumber)
                .orderByDesc(User::getMobilePhoneNumber));
        return BeanTransformUtils.transformList(list, UserVO.class);
    }

}
