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

    List<Map<String, Object>> getAllStudents();

}
