package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.query.UserQuery;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.util.BeanTransformUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @Resource
    private RestTemplate restTemplate;

    private static final String URL_PREFIX = "http://127.0.0.0:8080/user/";

    @GetMapping("/{userId}")
    @ApiOperation("通过主键获取用户")
    public UserVO getById(@PathVariable("userId") Long userId) {
        String url = URL_PREFIX + userId;
        //方式一：GET 方式获取 JSON 串数据
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("返回结果：" + result);

        //方式二：GET 方式获取 JSON 数据映射后的 Product 实体对象
        UserVO userVO = restTemplate.getForObject(url, UserVO.class);
        System.out.println("返回结果：" + userVO);

        //方式三：GET 方式获取包含 Product 实体对象 的响应实体 ResponseEntity 对象,用 getBody() 获取
        ResponseEntity<UserVO> responseEntity = restTemplate.getForEntity(url, UserVO.class);
        System.out.println("返回结果：" + responseEntity);

        return userVO;
    }

    @GetMapping
    @ApiOperation("查询所有的用户列表")
    public List<UserVO> list() {
        String url = URL_PREFIX;
        List<UserVO> list = restTemplate.getForObject(url, List.class);
        System.out.println("返回结果：" + list);

        return list;
    }

    @GetMapping("/page1")
    @ApiOperation("分页查询用户1")
    public CommonPageVO<UserVO> page1(@Valid UserQuery userQuery) {
        String url = URL_PREFIX + "page1";
        Map<String, String> map = BeanTransformUtils.transform(userQuery, Map.class);
        CommonPageVO<UserVO> page = restTemplate.getForObject(url, CommonPageVO.class, map);
        System.out.println("返回结果：" + page);
        return page;
    }

    @DeleteMapping("/{userId}")
    @ApiOperation("根据主键删除用户1")
    public boolean removeById(@PathVariable("userId") Long userId) {
        String url = URL_PREFIX + "{userId}";
        restTemplate.delete(url, userId);
        return true;
    }

    @PostMapping
    @ApiOperation("新增用户1")
    public boolean insertSelective(@Valid @RequestBody InsertUserDTO insertUserDTO) {
        return true;
    }

    @PutMapping("/{userId}")
    @ApiOperation("根据主键更新用户1")
    public boolean updateByPrimaryKeySelective(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        updateUserDTO.setUserId(userId);

        String url = URL_PREFIX + "{userId}";
        restTemplate.put(url,updateUserDTO,userId);

        return true;
    }

}
