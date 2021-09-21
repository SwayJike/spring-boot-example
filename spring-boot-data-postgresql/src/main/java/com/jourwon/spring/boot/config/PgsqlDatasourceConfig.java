package com.jourwon.spring.boot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * postgresql 数据源配置
 *
 * @author JourWon
 * @date 2021/9/21
 */
@Configuration
@MapperScan(basePackages = PgsqlDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "pgsqlSqlSessionFactory")
public class PgsqlDatasourceConfig {

    /**
     * mysqldao扫描路径
     */
    protected static final String PACKAGE = "com.jourwon.spring.boot.mapper";

    /**
     * mybatis mapper扫描路径
     */
    private static final String MAPPER_LOCATION = "classpath:mybatis/mapper/pgsql/*.xml";

    @Bean(name = "pgsqldatasource")
    @ConfigurationProperties("spring.datasource.druid.pgsql")
    public DataSource pgsqlDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "pgsqlTransactionManager")
    public DataSourceTransactionManager pgsqlTransactionManager() {
        return new DataSourceTransactionManager(pgsqlDatasource());
    }

    @Bean(name = "pgsqlSqlSessionFactory")
    public SqlSessionFactory pgsqlSqlSessionFactory(@Qualifier("pgsqldatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PgsqlDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
