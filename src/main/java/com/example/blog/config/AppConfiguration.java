package com.example.blog.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class AppConfiguration {
    @Bean
    public GroupedOpenApi publicArticleApi() {
        return GroupedOpenApi.builder()
                .group("Articles")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi(@Value("${application:description}")String appDescription,
                                 @Value("${application:version}")String appVersion) {
        return new OpenAPI().info(new Info().title("Application API")
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact().name("Tsirdava Mikhail")
                                .email("saitors16194@gmail.com")))
                ;
    }
}
