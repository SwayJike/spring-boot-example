package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * AsyncController
 *
 * @author JourWon
 * @date 2021/2/23
 */
@Slf4j
@Api(tags = {"异步调用"})
@RestController
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @GetMapping("/simplest")
    @ApiOperation("简单异步调用")
    public String asyncInvokeSimplest() {
        asyncService.asyncInvokeSimplest();
        return "simplest";
    }

    @GetMapping("/parameter")
    @ApiOperation("带参数的异步调用")
    public String asyncInvokeWithParameter() {
        asyncService.asyncInvokeWithParameter("thinkwon");
        return "parameter";
    }

    @GetMapping("/future")
    @ApiOperation("异常调用返回Future")
    public String asyncInvokeReturnFuture() {
        asyncService.asyncInvokeReturnFuture(3);
        return "future";
    }

    @GetMapping("/parameter_exception")
    @ApiOperation("带参数的异步调用-异常")
    public String asyncInvokeWithException() {
        asyncService.asyncInvokeWithException("thinkwon");
        return "parameter";
    }

    @GetMapping("/future_exception")
    @ApiOperation("异常调用返回Future-异常")
    public String asyncInvokeReturnFutureWithException() {
        asyncService.asyncInvokeReturnFutureWithException(3);
        return "future";
    }

}
