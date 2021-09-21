// package com.jourwon.spring.boot.dao.impl;
//
// import com.jourwon.spring.boot.dao.PgsqlStudentDao;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;
//
// import javax.annotation.Resource;
// import java.util.List;
// import java.util.Map;
//
// /**
//  * 学生 dao 实现类
//  *
//  * @author JourWon
//  * @date 2021/9/21
//  */
// @Repository
// public class PgsqlStudentDaoImpl implements PgsqlStudentDao {
//
//     @Resource
//     @Qualifier("pgsqlJdbcTemplate")
//     private JdbcTemplate jdbcTemplate;
//
//     @Override
//     public List<Map<String, Object>> getAllStudents() {
//         return this.jdbcTemplate.queryForList("select * from student");
//     }
//
// }
