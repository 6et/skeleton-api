package com.sixet.skeleton.core.config;

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

    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTROLLERS_PACKAGE = "com.sixet.core.business.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(Collections.singleton(APPLICATION_JSON))
                .produces(Collections.singleton(APPLICATION_JSON))
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLERS_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Skeleton Micro-Services",
                        "End points to maintain Micro-Services.",
                        "1.0.0",
                        "",
                        new Contact("Get Trevisan", "https://github.com/6et", "gtrevisane@gmail.com"),
                        "", "", Collections.emptyList()));
    }
}
