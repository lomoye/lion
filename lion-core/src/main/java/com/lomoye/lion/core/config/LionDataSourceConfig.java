package com.lomoye.lion.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:lion-core.properties")
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = "com.lomoye.lion.core.dao", sqlSessionFactoryRef = "lionSqlSessionFactory")
public class LionDataSourceConfig {

    @Value("${lion.datasource.url}")
    private String url;

    @Value("${lion.datasource.username}")
    private String user;

    @Value("${lion.datasource.password}")
    private String password;

    @Value("${lion.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "lionDataSource")
    @Primary
    public DataSource lionDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "lionTransactionManager")
    @Primary
    public DataSourceTransactionManager lionTransactionManager() {
        return new DataSourceTransactionManager(lionDataSource());
    }

    @Bean(name = "lionSqlSessionFactory")
    @Primary
    public SqlSessionFactory lionSqlSessionFactory(@Qualifier("lionDataSource") DataSource lionDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(lionDataSource);
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:lion_common_config.xml"));


        return sessionFactory.getObject();
    }
}