package com.j4v4developers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.j4v4developers"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(info());
    }
    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title("J4V4-Dev.com REST API created by Uğur Can Güleç")
                .description("J4V4-Dev.com API Documentation")
                .termsOfServiceUrl("https://opensource.org.licences/MIT")
                .license("MIT License")
                .version("1.12.3")
                .licenseUrl("https://opensource.org/license/MIT")
                .build();
    }

}
