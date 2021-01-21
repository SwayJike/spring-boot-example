package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.common.CommonPage;
import com.jourwon.spring.boot.enums.ResponseCodeEnum;
import com.jourwon.spring.boot.exception.CommonException;
import com.jourwon.spring.boot.response.CommonResponse;
import com.jourwon.spring.boot.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 后端响应格式示例
 *
 * @author JourWon
 * @date 2021/1/21
 */
@RestController
@RequestMapping("/demo")
@Api(tags = "【示例】后端响应格式")
public class DemoController {

    @GetMapping("/success_empty")
    @ApiOperation("无数据的成功响应")
    public CommonResponse<?> testSuccess() {
        return CommonResponse.success();
    }

    @GetMapping("/success_data")
    @ApiOperation("带数据的成功响应")
    public CommonResponse<UserVO> testSuccessData() {
        return CommonResponse.success(UserVO.builder().username("JourWon").build());
    }

    @GetMapping("/success_list")
    @ApiOperation("列表数据成功响应")
    public CommonResponse<List<UserVO>> testSuccessList() {
        List<UserVO> list = new ArrayList<>();
        list.add(UserVO.builder().username("JourWon").build());
        list.add(UserVO.builder().username("ThinkWon").build());
        return CommonResponse.success(list);
    }

    @GetMapping("/unauthorized_access")
    @ApiOperation("访问未授权")
    public CommonResponse<?> testAuthFail() {
        return CommonResponse.failure(ResponseCodeEnum.UNAUTHORIZED_ACCESS);
    }

    @GetMapping("/exception")
    @ApiOperation("异常响应")
    public CommonResponse<?> testUnknownException() {
        throw new CommonException(ResponseCodeEnum.SYS_EXCEPTION);
    }

    @GetMapping("/timeout")
    @ApiOperation("系统执行超时")
    public CommonResponse<?> testTimeout() {
        throw new CommonException(ResponseCodeEnum.SYSTEM_EXECUTION_TIMEOUT);
    }

    @GetMapping("/page")
    @ApiOperation("分页数据格式示例")
    public CommonResponse<CommonPage<UserVO>> testPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        List<UserVO> list = new ArrayList<>(4);
        list.add(UserVO.builder()
                .userId(1L)
                .username("JourWon")
                .mobilePhoneNumber("13800000000")
                .email("JourWon@163.com")
                .deleteState((short) 0)
                .createTime(LocalDateTime.now())
                .updateTime(null)
                .build());
        list.add(UserVO.builder()
                .userId(2L)
                .username("Jobs")
                .mobilePhoneNumber("13800000055")
                .email("Jobs@163.com")
                .deleteState((short) 0)
                .createTime(LocalDateTime.now())
                .updateTime(null)
                .build());
        list.add(UserVO.builder()
                .userId(3L)
                .username("Bill Gates")
                .mobilePhoneNumber("13800000066")
                .email("Bill Gates@163.com")
                .deleteState((short) 0)
                .createTime(LocalDateTime.now())
                .updateTime(null)
                .build());
        list.add(UserVO.builder()
                .userId(4L)
                .username("Buffett")
                .mobilePhoneNumber("13800000077")
                .email("Buffett@163.com")
                .deleteState((short) 0)
                .createTime(LocalDateTime.now())
                .updateTime(null)
                .build());

        CommonPage<UserVO> page = new CommonPage<>(pageNum,
                pageSize, 4, 1, 4, list);

        return CommonResponse.success(page);
    }
}
