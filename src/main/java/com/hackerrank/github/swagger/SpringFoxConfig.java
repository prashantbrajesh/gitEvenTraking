package com.hackerrank.github.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }


    private ApiInfo apiInfo() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("Hrank Test REST API");
        apiInfoBuilder.description("Hrank");
        apiInfoBuilder.version("0.0.1-SNAPSHOT");
        apiInfoBuilder.contact(new Contact("Help", "https://hrank", "help@hrank.in"));
        apiInfoBuilder.license("Open LICENSE, Version 0.1");
        apiInfoBuilder.licenseUrl("https://hrank.in");

        return apiInfoBuilder.build();
    }
}
