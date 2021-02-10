package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.dto.UserDTO;

import java.util.List;

/**
 * 用户service接口
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserService {

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
     * 根据主键删除用户
     *
     * @param userId 用户id
     * @return int
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增用户
     *
     * @param insertUserDTO 新增用户DTO
     * @return int
     */
    int insert(InsertUserDTO insertUserDTO);

    /**
     * 可选择的新增用户
     *
     * @param insertUserDTO 新增用户DTO
     * @return int
     */
    int insertSelective(InsertUserDTO insertUserDTO);

    /**
     *
     * 根据主键可选择的更新用户
     *
     * @param updateUserDTO 更新用户DTO
     * @return int
     */
    int updateByPrimaryKeySelective(UpdateUserDTO updateUserDTO);

    /**
     * 根据主键更新用户
     *
     * @param updateUserDTO  更新用户DTO
     * @return int
     */
    int updateByPrimaryKey(UpdateUserDTO updateUserDTO);

}
