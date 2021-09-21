package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.mapper.PgsqlStudentMapper;
import com.jourwon.spring.boot.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学生 service 实现类
 *
 * @author JourWon
 * @date 2021/9/21
 */
@Service
public class StudentServiceImpl implements StudentService {

    // @Resource
    // private PgsqlStudentDao pgsqlStudentDao;

    @Resource
    private PgsqlStudentMapper pgsqlStudentMapper;

    @Override
    public List<Map<String, Object>> getAllStudentsWithMysql() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getAllStudentsWithOracle() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getAllStudentsWithPgsql() {
        return pgsqlStudentMapper.getAllStudents();
    }

}
