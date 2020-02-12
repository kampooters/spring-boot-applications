package com.org.assignment.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * Configurations for Swager-2.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.org.assignment")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "REST APIs",
                "Rest APIs provide the CRUD operations binded with HTTP methods",
                "v1",
                "Terms of service",
                new Contact("Abdulrehman", "ww.linkedin.com/in/abdulrehmanabdul", "abdulrehman.abdul.qau@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}