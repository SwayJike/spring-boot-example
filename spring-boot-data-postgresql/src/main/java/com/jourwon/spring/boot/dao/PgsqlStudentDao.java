package com.jourwon.spring.boot.dao;

import java.util.List;
import java.util.Map;

/**
 * 学生 dao
 *
 * @author JourWon
 * @date 2021/9/21
 */
public interface PgsqlStudentDao {

    /**
     * 获取所有学生列表
     *
     * @return List<Map<String, Object>> 所有学生列表
     */
    List<Map<String, Object>> getAllStudents();

}
