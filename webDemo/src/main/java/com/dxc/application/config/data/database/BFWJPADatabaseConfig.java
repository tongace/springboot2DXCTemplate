package com.dxc.application.config.data.database;

import com.dxc.application.properties.AppDataSourceProperties;
import com.dxc.application.properties.BfwJpaDataSourceProperties;
import com.dxc.application.properties.JpaProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
public class BFWJPADatabaseConfig {
    private AppDataSourceProperties appDataSourceProperties;
    private BfwJpaDataSourceProperties bfwDataSourceProperties;
    private JpaProperties jpaProperties;

    @Autowired
    public BFWJPADatabaseConfig(AppDataSourceProperties appDataSourceProperties, BfwJpaDataSourceProperties bfwDataSourceProperties, JpaProperties jpaProperties) {
        this.appDataSourceProperties = appDataSourceProperties;
        this.bfwDataSourceProperties = bfwDataSourceProperties;
        this.jpaProperties = jpaProperties;
    }

    @Bean(name="dataSource_bfw")
    public DataSource getBFWJPADataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(appDataSourceProperties.getDriverClassName());
        ds.setUrl(bfwDataSourceProperties.getUrl());
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

    @Bean(name="entityManagerFactory_bfw")
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

    @Bean(name="bfw")
    public PlatformTransactionManager bfwTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getBFWEntityManagerFactory().getObject());
        return transactionManager;
    }
}
