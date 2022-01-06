package com.business.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendBusinessTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBusinessTasksApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/employees").allowedOrigins("*");
                registry.addMapping("/api/enterprises").allowedOrigins("*");
                registry.addMapping("/api/tasks").allowedOrigins("*");
                registry.addMapping("/api/users").allowedOrigins("*");
                registry.addMapping("/api/validate").allowedOrigins("*");
            }
        };
    }
}
