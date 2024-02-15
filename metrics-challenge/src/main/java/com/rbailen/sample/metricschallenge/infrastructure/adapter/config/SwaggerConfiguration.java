package com.rbailen.sample.metricschallenge.infrastructure.adapter.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components())
                .info(
                        new Info()
                                .title("PRODUCTS API")
                                .description("API that receives a set of metrics and weights associated with each metric and returns the list of ordered products")
                                .version("1.0")
                .contact(
                        new Contact()
                                .name("rbailen")
                                .url("https://rbailen.github.io")
                ));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("ProductsAPI")
                .pathsToMatch("/api/**")
                .build();
    }

}