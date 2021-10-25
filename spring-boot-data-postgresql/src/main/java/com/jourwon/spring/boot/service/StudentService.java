package com.jourwon.spring.boot.service;

import java.util.List;
import java.util.Map;

/**
 * 学生 service 接口
 *
 * @author JourWon
 * @date 2021/9/21
 */
public interface StudentService {

    /**
     * 从MySQL数据库获取所有的学生列表
     *
     * @return List<Map<String, Object>> 所有的学生列表
     */
    List<Map<String, Object>> getAllStudentsWithMysql();

    /**
     * 从Oracle数据库获取所有的学生列表
     *
     * @return List<Map<String, Object>> 所有的学生列表
     */
    List<Map<String, Object>> getAllStudentsWithOracle();

    /**
     * 从Postgresql数据库获取所有的学生列表
     *
     * @return List<Map<String, Object>> 所有的学生列表
     */
    List<Map<String, Object>> getAllStudentsWithPgsql();

}
