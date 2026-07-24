package com.example.gastos_fianceiros.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable()) // <--- ESSIDIAL: Desativa o CSRF para APIs REST
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                            // Libera explicitamente as rotas de autenticação e registo
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                            // Qualquer outra rota exige autenticação via Token JWT
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

    // Bean para definir quais origens, métodos e headers são permitidos
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Libera para qualquer origem usando padrão de asterisco seguro
        configuration.setAllowedOriginPatterns(List.of("*")); 
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        // Cabeçalhos permitidos (incluindo o Authorization para o JWT)
        configuration.setAllowedHeaders(List.of("*"));
        
        // Permite o envio de credenciais (como tokens de autorização e cookies)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}