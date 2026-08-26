package com.example.gastos_fianceiros.infrastructure.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica para todas as rotas da API
                .allowedOrigins(
                    "https://ui-resqistro-gasto.vercel.app", // URL da sua aplicação na Vercel
                    "http://localhost:5000",
                    "http://localhost:5001"                 // Caso queira testar build local simulando produção
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Métodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
