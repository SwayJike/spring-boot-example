package com.jourwon.spring.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;

import java.util.List;

/**
 * 用户表 服务类
 *
 * @author JourWon
 * @date 2021-02-10
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户
     *
     * @param userQuery 用户查询参数
     * @return CommonPageVO<UserVO> 分页数据结构
     */
    CommonPageVO<UserVO> page1(UserQuery userQuery);

    /**
     * 分页查询用户
     *
     * @param userQuery 用户查询参数
     * @return CommonPageVO<UserVO> 分页数据结构
     */
    CommonPageVO<UserVO> page2(UserQuery userQuery);

    /**
     * 分页查询用户
     *
     * @param userQuery 用户查询参数
     * @return CommonPageVO<UserVO> 分页数据结构
     */
    CommonPageVO<UserVO> page3(UserQuery userQuery);

    /**
     * 新增用户
     *
     * @param insertUserDTO 新增用户DTO
     * @return boolean
     */
    boolean saveUser(InsertUserDTO insertUserDTO);

    /**
     * 更新用户
     *
     * @param updateUserDTO 更新用户DTO
     * @return boolean
     */
    boolean updateUser(UpdateUserDTO updateUserDTO);

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return boolean
     */
    boolean removeUser(Long userId);

    /**
     * 根据手机号码倒序
     *
     * @return List<UserVO>
     */
    List<UserVO> orderByDescMobilePhoneNumber();

}
