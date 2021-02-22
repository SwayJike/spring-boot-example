package com.jourwon.spring.boot.dao;

import com.jourwon.spring.boot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserDao
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    User findByUsername(String username);

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
     * @return List<User>
     */
    List<User> findByEmailLike(String email);

    /**
     * 根据用户名更新用户,原生代码的查询方式,更新操作需要添加事务注解
     *
     * @param username 用户名
     * @param userId   用户id
     * @return Integer
     */
    @Transactional
    @Modifying
    @Query(value = "update user u set u.username = ?1 where u.user_id = ?2", nativeQuery = true)
    Integer updateByUsernameAndUserId(String username, Long userId);

    /**
     * 根据邮箱查询用户,原生代码的查询方式
     *
     * @param email 邮箱
     * @return User
     */
    @Query(value = "select * from user u where u.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

}
