package com.dxc.application.config.data.database;

import com.dxc.application.properties.AppDataSourceProperties;
import com.dxc.application.properties.JpaProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class AppJPADatabaseConfig {
    private final AppDataSourceProperties appDataSourceProperties;
    private final JpaProperties jpaProperties;


    @Bean(name = "jpaDataSource")
    public DataSource getJPADataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(appDataSourceProperties.getMaximumPoolSize());
        ds.setDriverClassName(appDataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(appDataSourceProperties.getUrl());
        ds.setUsername(appDataSourceProperties.getUsername());
        ds.setPassword(appDataSourceProperties.getPassword());
        return ds;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", jpaProperties.getHibernateDialect());
        properties.setProperty("hibernate.show_sql", jpaProperties.getShowSql());
        return properties;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getJPADataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.parseBoolean(jpaProperties.getShowSql()));
        vendorAdapter.setDatabase(Database.valueOf(jpaProperties.getDatabase()));
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName("st3main");
        em.setPackagesToScan("com.dxc.application.model", "th.co.toyota.st3.api.model");
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor jpaExceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
