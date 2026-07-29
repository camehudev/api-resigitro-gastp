package com.example.gastos_fianceiros.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    // Defina o nome exato do cookie que o seu backend utiliza para salvar o token
    private static final String JWT_COOKIE_NAME = "jwt_token"; // Ajuste se o nome no seu projeto for diferente (ex: "SESSION", "token", etc.)

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var token = recuperarToken(request);

        if (token != null) {
            var subject = tokenService.validarToken(token);
            if (subject != null && !subject.isEmpty()) {
                // Token válido, injeta a autenticação no contexto do Spring Security
                var authentication = new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        // 1. Tenta recuperar do cabeçalho Authorization (Bearer Token)
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        // 2. Se não estiver no cabeçalho, tenta recuperar do Cookie HttpOnly
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (JWT_COOKIE_NAME.equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null && !cookieValue.trim().isEmpty()) {
                        return cookieValue;
                    }
                }
            }
        }

        return null;
    }
}