package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学生 controller
 *
 * @author JourWon
 * @date 2021/9/21
 */
@RestController
@RequestMapping("/student")
@Api(tags = {"学生接口"})
public class StudentController {

    @Resource
    private StudentService studentService;

    @ApiOperation("从MySQL数据库获取所有学生")
    @GetMapping(value = "/getAllStudentsWithMysql")
    public List<Map<String, Object>> getAllStudentsWithMysql() {
        return this.studentService.getAllStudentsWithMysql();
    }

    @ApiOperation("从Oracle数据库获取所有学生")
    @GetMapping(value = "/getAllStudentsWithOracle")
    public List<Map<String, Object>> getAllStudentsWithOracle() {
        return this.studentService.getAllStudentsWithOracle();
    }

    @ApiOperation("从PostgreSQL数据库获取所有学生")
    @GetMapping(value = "/getAllStudentsWithPgsql")
    public List<Map<String, Object>> getAllStudentsWithPgsql() {
        return this.studentService.getAllStudentsWithPgsql();
    }

}
