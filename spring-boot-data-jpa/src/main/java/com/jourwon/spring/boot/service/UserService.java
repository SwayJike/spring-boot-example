package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.dto.UserDTO;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;

import java.util.List;

/**
 * 用户service接口
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserService {

    /**
     * 分页查询用户
     *
     * @param userQuery 用户查询参数
     * @return CommonPageVO<UserVO> 分页数据结构
     */
    CommonPageVO<UserVO> pageBySpecification(UserQuery userQuery);

    /**
     * 通过主键获取用户
     *
     * @param userId 用户id
     * @return UserDTO
     */
    UserDTO getByPrimaryKey(Long userId);

    /**
     * 查询所有的用户列表
     *
     * @return List<UserDTO>
     */
    List<UserDTO> listUsers();

    /**
     * 分页查询用户
     *
     * @param userQuery 用户查询参数
     * @return CommonPageVO<UserVO> 分页数据结构
     */
    CommonPageVO<UserVO> page(UserQuery userQuery);

    /**
     * 根据主键删除用户
     *
     * @param userId 用户id
     * @return boolean
     */
    boolean deleteByPrimaryKey(Long userId);

    /**
     * 可选择的新增用户
     *
     * @param insertUserDTO insertUserDTO
     * @return boolean
     */
    boolean insert(InsertUserDTO insertUserDTO);

    /**
     *
     * 根据主键可选择的更新用户
     *
     * @param updateUserDTO updateUserDTO
     * @return boolean
     */
    boolean updateByPrimaryKey(UpdateUserDTO updateUserDTO);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return UserDTO
     */
    UserDTO findByUsername(String username);

    /**
     * 统计用户名的数量
     *
     * @param username 用户名
     * @return Long
     */
    Long countByUsername(String username);

    /**
     * 根据邮箱模糊查询用户
     *
     * @param email 邮箱
     * @return List<UserDTO>
     */
    List<UserDTO> findByEmailLike(String email);

    /**
     * 根据用户名更新用户
     *
     * @param username 用户名
     * @param userId 用户id
     * @return Integer
     */
    Integer updateByUsernameAndUserId(String username, Long userId);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return User
     */
    UserDTO findByEmail(String email);

}
