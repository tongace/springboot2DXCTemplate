package com.dxc.application.config.data.database;

import com.dxc.application.properties.AppDataSourceProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@Configuration
@EnableTransactionManagement
public class AppMyBatisDatabaseConfig {
    private AppDataSourceProperties appDataSourceProperties;

    @Autowired
    public AppMyBatisDatabaseConfig(AppDataSourceProperties appDataSourceProperties) {
        this.appDataSourceProperties = appDataSourceProperties;
    }

    @Bean(name = "myBatisDataSource")
    @Primary
    public DataSource getMyBatisDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(appDataSourceProperties.getDriverClassName());
        ds.setUrl(appDataSourceProperties.getUrl());
        ds.setUsername(appDataSourceProperties.getUsername());
        ds.setPassword(appDataSourceProperties.getPassword());
        return ds;
    }

    @Bean(name="mybastistx")
    public DataSourceTransactionManager getMyBatisTransactionManager() {
        return new DataSourceTransactionManager(getMyBatisDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(getMyBatisDataSource());
        SqlSessionFactory sessionFactory = sessionFactoryBean.getObject();
        sessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory;
    }
}
