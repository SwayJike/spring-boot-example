package com.jourwon.spring.boot.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.query.PageQuery;
import com.jourwon.spring.boot.query.UserQuery;
import com.jourwon.spring.boot.response.CommonPage;
import com.jourwon.spring.boot.response.CommonResponse;
import com.jourwon.spring.boot.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 用户controller,使用restful规范
 *
 * @author JourWon
 * @date 2021/1/24
 */
@Api(tags = "用户")
@ApiSupport(author = "JourWon@163.com")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final List<UserVO> LIST = new ArrayList<UserVO>() {
        {
            add(UserVO.builder()
                    .userId(1L).username("JourWon").mobilePhoneNumber("13800000000")
                    .email("JourWon@163.com").deleteState((short) 0)
                    .createTime(LocalDateTime.now()).updateTime(null)
                    .build());
            add(UserVO.builder()
                    .userId(2L).username("Jobs").mobilePhoneNumber("13800000055")
                    .email("Jobs@163.com").deleteState((short) 0)
                    .createTime(LocalDateTime.now()).updateTime(null)
                    .build());
            add(UserVO.builder()
                    .userId(3L).username("Bill Gates").mobilePhoneNumber("13800000066")
                    .email("Bill Gates@163.com").deleteState((short) 0)
                    .createTime(LocalDateTime.now()).updateTime(null)
                    .build());
            add(UserVO.builder()
                    .userId(4L).username("Buffett").mobilePhoneNumber("13800000077")
                    .email("Buffett@163.com").deleteState((short) 0)
                    .createTime(LocalDateTime.now()).updateTime(null)
                    .build());
        }
    };

    @ApiOperationSupport(order = 1)
    @GetMapping("/{userId}")
    @ApiOperation("根据用户id获取用户")
    public CommonResponse<UserVO> get(@PathVariable("userId") Long userId) {
        for (UserVO userVO : LIST) {
            if (userId.equals(userVO.getUserId())) {
                return CommonResponse.success(userVO);
            }
        }

        return CommonResponse.failure(CommonResponseCodeEnum.USER_ID_NOT_EXIST);
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("获取所有用户列表")
    public CommonResponse<List<UserVO>> list() {
        return CommonResponse.success(LIST);
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/page")
    @ApiOperation("分页查询用户列表")
    public CommonResponse<CommonPage<UserVO>> page(@Valid PageQuery pageQuery) {
        List<UserVO> userList = new ArrayList<>();

        int pageSize = pageQuery.getPageSize();
        int pageNum = pageQuery.getPageNum();

        int size = LIST.size();
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(pageNum * pageSize, size);

        for (int i = start; i < end; i++) {
            userList.add(LIST.get(i));
        }

        int pages = (size + pageSize - 1) / pageSize;
        CommonPage<UserVO> page = new CommonPage<>(pageNum,
                pageSize, userList.size(), pages, size, userList);

        return CommonResponse.success(page);
    }

    @ApiOperationSupport(order = 4)
    @PostMapping
    @ApiOperation("新增用户")
    public CommonResponse<?> insert(@Valid @RequestBody UserQuery userQuery) {
        Optional<UserVO> optional = LIST.stream().max(Comparator.comparing(UserVO::getUserId));
        UserVO userVO = null;
        if (optional.isPresent()) {
            userVO = UserVO.builder().userId(optional.get().getUserId() + 1L).username(userQuery.getUsername())
                    .mobilePhoneNumber(userQuery.getMobilePhoneNumber()).email(userQuery.getEmail())
                    .deleteState((short) 0).createTime(LocalDateTime.now()).updateTime(null).build();
        }
        LIST.add(userVO);
        return CommonResponse.success("新增用户成功");
    }

    @ApiOperationSupport(order = 5)
    @PutMapping("/{userId}")
    @ApiOperation("根据用户id更新用户")
    public CommonResponse<?> update(@PathVariable("userId") Long userId, @Valid @RequestBody UserQuery userQuery) {
        for (UserVO userVO : LIST) {
            if (userId.equals(userVO.getUserId())) {
                userVO.setUsername(userQuery.getUsername());
                userVO.setMobilePhoneNumber(userQuery.getMobilePhoneNumber());
                userVO.setEmail(userQuery.getEmail());
                userVO.setUpdateTime(LocalDateTime.now());
                return CommonResponse.success();
            }
        }

        return CommonResponse.failure(CommonResponseCodeEnum.USER_ID_NOT_EXIST);
    }

    @ApiOperationSupport(order = 6)
    @DeleteMapping("/{userId}")
    @ApiOperation("根据用户id删除用户")
    public CommonResponse<?> delete(@PathVariable("userId") Long userId) {
        Iterator<UserVO> iterator = LIST.iterator();
        while (iterator.hasNext()) {
            if (userId.equals(iterator.next().getUserId())) {
                iterator.remove();
                return CommonResponse.success();
            }
        }

        return CommonResponse.failure(CommonResponseCodeEnum.USER_ID_NOT_EXIST);
    }

}
