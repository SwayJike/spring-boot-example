package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.dto.InsertUserDTO;
import com.jourwon.spring.boot.dto.UpdateUserDTO;
import com.jourwon.spring.boot.dto.UserDTO;

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
     * @param userId
     * @return
     */
    UserDTO getByPrimaryKey(Long userId);

    /**
     * 查询所有的用户列表
     *
     * @return
     */
    List<UserDTO> listUsers();

    /**
     * 根据主键删除用户
     *
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增用户
     *
     * @param insertUserDTO
     * @return
     */
    int insert(InsertUserDTO insertUserDTO);

    /**
     * 可选择的新增用户
     *
     * @param insertUserDTO
     * @return
     */
    int insertSelective(InsertUserDTO insertUserDTO);

    /**
     *
     * 根据主键可选择的更新用户
     *
     * @param updateUserDTO
     * @return
     */
    int updateByPrimaryKeySelective(UpdateUserDTO updateUserDTO);

    /**
     * 根据主键更新用户
     *
     * @param updateUserDTO
     * @return
     */
    int updateByPrimaryKey(UpdateUserDTO updateUserDTO);

}
