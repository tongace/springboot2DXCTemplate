package com.dxc.application.config.data.database;

import com.dxc.application.properties.AppDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(appDataSourceProperties.getMaximumPoolSize());
        ds.setDriverClassName(appDataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(appDataSourceProperties.getUrl());
        ds.setUsername(appDataSourceProperties.getUsername());
        ds.setPassword(appDataSourceProperties.getPassword());
        return ds;
    }

    @Bean(name = "mybastistx")
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
