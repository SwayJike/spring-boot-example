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

}
