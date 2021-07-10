package com.jourwon.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jourwon.spring.boot.mapper.UserMapper;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表 服务实现类
 *
 * @author JourWon
 * @date 2021-02-10
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 方式一：直接引入 Caffeine 依赖，然后使用 Caffeine 方法实现缓存。
    // @Resource
    // private Cache<String, Object> caffeineCache;
    //
    // @Override
    // public User getByUserId(Long userId) {
    //     // 先从缓存读取
    //     User user = (User) caffeineCache.asMap().get(String.valueOf(userId));
    //     if (user != null) {
    //         log.info("缓存中有数据,用户信息:{}", user);
    //         return user;
    //     }
    //     // 如果缓存中不存在，则从库中查找
    //     user = this.getById(userId);
    //     log.info("查询了数据库,用户信息:{}", user);
    //     // 如果用户信息不为空，则加入缓存
    //     if (user != null) {
    //         caffeineCache.put(String.valueOf(user.getUserId()), user);
    //     }
    //     return user;
    // }
    //
    // @Override
    // public boolean saveUser(InsertUserDTO insertUserDTO) {
    //     User user = BeanTransformUtils.transform(insertUserDTO, User.class);
    //     boolean flag = this.save(user);
    //     // 加入缓存,更合理的做法是再次从数据库查询出来
    //     caffeineCache.put(String.valueOf(user.getUserId()), user);
    //     return flag;
    // }
    //
    // @Override
    // public boolean updateUser(UpdateUserDTO updateUserDTO) {
    //     User user = BeanTransformUtils.transform(updateUserDTO, User.class);
    //     boolean b = this.updateById(user);
    //     // 替换缓存中的值,更合理的做法是再次从数据库查询出来
    //     caffeineCache.put(String.valueOf(user.getUserId()), user);
    //     return b;
    // }
    //
    // @Override
    // public boolean removeUser(Long userId) {
    //     boolean b = this.removeById(userId);
    //     // 从缓存中删除
    //     caffeineCache.asMap().remove(String.valueOf(userId));
    //     return b;
    // }

    /**
     * 方式二：引入 Caffeine 和 Spring Cache 依赖，使用 SpringCache 注解方法实现缓存。
     */
    @Resource
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "user", key = "#userId")
    public User getByUserId(Long userId) {
        User user = this.getById(userId);
        log.info("查询了数据库,用户信息:{}", user);
        return user;
    }

    @Override
    public boolean saveUser(InsertUserDTO insertUserDTO) {
        User user = BeanTransformUtils.transform(insertUserDTO, User.class);
        int count = userMapper.insert(user);
        return count >= 1;
    }

    @Override
    @CachePut(value = "user", key = "#updateUserDTO.userId")
    public boolean updateUser(UpdateUserDTO updateUserDTO) {
        User user = BeanTransformUtils.transform(updateUserDTO, User.class);
        return this.updateById(user);
    }

    @Override
    @CacheEvict(value = "user", key = "#userId")
    public boolean removeUser(Long userId) {
        return this.removeById(userId);
    }

}
