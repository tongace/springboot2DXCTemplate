package com.dxc.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@PropertySource({"classpath:demo-application.properties"})
@PropertySource({"classpath:data-access.properties"})
@ConfigurationPropertiesScan
public class WebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/usermaster/*"))
                .paths(PathSelectors.regex("(?!/js).+"))
                .apis(RequestHandlerSelectors.basePackage("com.dxc"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "DXC Demo API",
                "Sample API template",
                "1.0.0",
                "Free to use",
                new springfox.documentation.service.Contact("DXC Technology Thailand","https://www.dxc.com/sk/en","xxx@dxc.com"),
                "MIT License",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList()
        );
    }
}
