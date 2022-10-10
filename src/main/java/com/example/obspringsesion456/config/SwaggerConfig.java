package com.example.obspringsesion456.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

// http://localhost:8080/swagger-ui/
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Spring Boot Api Rest",
                "Documentacion Api Rest",
                "1.0",
                "http://www.google.com.ar",
                new Contact("Guillermo", "http://google.com.ar", "guille@correo.com.ar"),
                "MIT",
                "http://www.google.com.ar",
                Collections.emptyList());
    }

}
