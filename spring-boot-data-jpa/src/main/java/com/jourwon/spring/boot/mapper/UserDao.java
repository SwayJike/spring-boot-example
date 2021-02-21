package com.jourwon.spring.boot.mapper;

import com.jourwon.spring.boot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserDao
 *
 * @author JourWon
 * @date 2021/2/5
 */
public interface UserDao extends JpaRepository<User, Long> {

}
