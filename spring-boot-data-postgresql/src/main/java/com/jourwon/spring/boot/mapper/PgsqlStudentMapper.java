package com.jourwon.spring.boot.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学生 mapper 接口
 *
 * @author JourWon
 * @date 2021/9/21
 */
@Repository("pgsqlStudentMapper")
public interface PgsqlStudentMapper {

    /**
     * 查询所有的学生
     *
     * @return
     */
    List<Map<String, Object>> getAllStudents();

}
