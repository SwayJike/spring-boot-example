package com.jourwon.spring.boot.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.entity.User;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.ConcurrentMap;

/**
 * 用户表 前端控制器
 *
 * @author JourWon
 * @date 2021-02-10
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    // 方式二：引入 Caffeine 和 Spring Cache 依赖，使用 SpringCache 注解方法实现缓存。
    @Resource
    private CacheManager cacheManager;

    @Resource
    private UserService userService;

    @PostMapping
    @ApiOperation("新增用户")
    public boolean insertSelective(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        boolean b = userService.saveUser(insertUserDTO);
        return b;
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("根据主键删除用户")
    public boolean removeUser(@PathVariable("userId") Long userId) {
        return userService.removeUser(userId);
    }

    @PutMapping("/{userId}")
    @ApiOperation("根据主键更新用户")
    public boolean updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        updateUserDTO.setUserId(userId);
        return userService.updateUser(updateUserDTO);
    }

    @GetMapping("/{userId}")
    @ApiOperation("通过主键获取用户")
    public UserVO getById(@PathVariable("userId") Long userId) {
        User user = userService.getByUserId(userId);
        return BeanTransformUtils.transform(user, UserVO.class);
    }

    @GetMapping("/stats")
    @ApiOperation("缓存统计信息")
    public String stats() {
        CaffeineCache caffeine = (CaffeineCache) cacheManager.getCache("user");
        Cache user = caffeine.getNativeCache();
        String statsInfo = "cache名字:user<br/>";
        Long size = user.estimatedSize();
        statsInfo += "size:" + size + "<br/>";
        ConcurrentMap map = user.asMap();
        statsInfo += "map keys:<br/>";
        for (Object key : map.keySet()) {
            statsInfo += "key:" + key.toString() + ";value:" + map.get(key) + "<br/>";
        }
        statsInfo += "统计信息:" + user.stats().toString();
        return statsInfo;
    }

}
