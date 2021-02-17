package com.jourwon.spring.boot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置
 *
 * @author JourWon
 * @date 2021/2/17
 */
@Configuration
@MapperScan(basePackages = "com.jourwon.spring.boot.mapper.demo", sqlSessionTemplateRef = "demoSqlSessionTemplate")
public class DataSourceDemoConfig {

    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "demoSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("demoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 如果使用的是注解方式,可以注释下面这行代码
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/demo/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "demoTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("demoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "demoSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("demoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
