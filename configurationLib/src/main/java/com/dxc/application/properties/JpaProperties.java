package com.dxc.application.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConstructorBinding
@ConfigurationProperties(prefix = "jpa")
@AllArgsConstructor
@Getter
public class JpaProperties {
    private final String hibernateDialect;
    private final String database;
    private final String showSql;
}
