// package com.jourwon.spring.boot.config;
//
// import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.core.JdbcTemplate;
//
// import javax.sql.DataSource;
//
// /**
//  * 数据源配置
//  *
//  * @author JourWon
//  * @date 2021/9/21
//  */
// @Configuration
// public class DataSourceConfig {
//
//     @Bean(name = "pgsqldatasource")
//     @ConfigurationProperties("spring.datasource.druid.pgsql")
//     public DataSource dataSourceThree() {
//         return DruidDataSourceBuilder.create().build();
//     }
//
//
//     @Bean(name = "pgsqlJdbcTemplate")
//     public JdbcTemplate pgsqlJdbcTemplate(
//             @Qualifier("pgsqldatasource") DataSource dataSource) {
//         return new JdbcTemplate(dataSource);
//     }
//
// }
