package com.dxc.application.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConstructorBinding
@ConfigurationProperties(prefix = "app.jdbc")
@AllArgsConstructor
@Getter
public class AppDataSourceProperties {
    private final String driverClassName;
    private final Integer maximumPoolSize;
    private final String url;
    private final String username;
    private final String password;
}
