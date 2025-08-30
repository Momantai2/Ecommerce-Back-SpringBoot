package com.ecommerce.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // Más específico que "/**"
                        .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                        .allowedMethods("*")  // Permite todos los métodos
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization", "Content-Disposition")  // Headers expuestos
                        .allowCredentials(true)
                        .maxAge(3600);  // Cache de preflight por 1 hora
            }
        };
    }
}