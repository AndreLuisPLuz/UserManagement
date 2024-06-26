package com.trevis.startup.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("null")
            .allowedMethods(
                "GET", "POST", "PUT", "PATCH", "DELETE", 
                "OPTIONS", "HEAD", "TRACE", "CONNECT"
            );
    }
}