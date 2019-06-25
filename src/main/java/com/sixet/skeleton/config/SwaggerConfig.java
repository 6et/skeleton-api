package com.sixet.skeleton.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * SwaggerConfig class provides the configuration of swagger.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.api.title}")
    private String title;

    @Value("${swagger.api.description}")
    private String description;

    @Value("${swagger.api.version}")
    private String version;

    @Value("${swagger.api.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${swagger.content.type}")
    private String contentType;

    @Value("${swagger.controller.basepackage}")
    private String basePackage;

    @Value("${swagger.contact.name}")
    private String name;

    @Value("${swagger.contact.url}")
    private String url;

    @Value("${swagger.contact.email}")
    private String email;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .consumes(Collections.singleton(contentType))
                .produces(Collections.singleton(contentType))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.ant("/technologies/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
      return new ApiInfo(
                title,
                description,
                version,
                termsOfServiceUrl,
                new Contact(name, url, email),
                "", "", Collections.emptyList());
    }
}
