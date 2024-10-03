package com.onb.Onboarding.infrastructure.adapters.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Ruta de la API
                .allowedOrigins("http://localhost:3030") // Origen permitido (tu frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*")
                .allowCredentials(true); // Cabeceras permitidas
    }
}
