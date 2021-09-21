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

    List<Map<String, Object>> getAllStudentsWithMysql();

    List<Map<String, Object>> getAllStudentsWithOracle();

    List<Map<String, Object>> getAllStudentsWithPgsql();

}
