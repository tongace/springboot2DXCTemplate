package com.dxc.application.config.data.database;

import com.dxc.application.properties.AppDataSourceProperties;
import com.dxc.application.properties.BfwJpaDataSourceProperties;
import com.dxc.application.properties.JpaProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class BFWJPADatabaseConfig {
    private final AppDataSourceProperties appDataSourceProperties;
    private final BfwJpaDataSourceProperties bfwDataSourceProperties;
    private final JpaProperties jpaProperties;

    @Bean(name = "dataSource_bfw")
    public DataSource getBFWJPADataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(appDataSourceProperties.getMaximumPoolSize());
        ds.setDriverClassName(appDataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(bfwDataSourceProperties.getUrl());
        ds.setUsername(bfwDataSourceProperties.getUsername());
        ds.setPassword(bfwDataSourceProperties.getPassword());
        return ds;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", jpaProperties.getHibernateDialect());
        properties.setProperty("hibernate.show_sql", jpaProperties.getShowSql());
        return properties;
    }

    @Bean(name = "entityManagerFactory_bfw")
    public LocalContainerEntityManagerFactoryBean getBFWEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getBFWJPADataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.parseBoolean(jpaProperties.getShowSql()));
        vendorAdapter.setDatabase(Database.valueOf(jpaProperties.getDatabase()));
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName("st3main_bfw");
        em.setPackagesToScan("th.co.toyota.st3.api.model");
        return em;
    }

    @Bean(name = "bfw")
    public PlatformTransactionManager bfwTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getBFWEntityManagerFactory().getObject());
        return transactionManager;
    }
}
