package com.example.gastos_fianceiros.application.service;

import com.example.gastos_fianceiros.DTO.LoginDTO;
import com.example.gastos_fianceiros.DTO.RegisterDTO;
import com.example.gastos_fianceiros.DTO.TokenDTO;
import com.example.gastos_fianceiros.domain.model.Usuario;
import com.example.gastos_fianceiros.infrastructure.repository.UsuarioRepository;
import com.example.gastos_fianceiros.infrastructure.security.TokenService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class AutenticacaoService {

    @Value("${api.security.token.secret}") // Chave secreta configurada no seu application.properties
    private String secretKey;

    /**
     * Valida a assinatura e a expiração de um token JWT.
     * 
     * @param token O token JWT em formato string
     * @return boolean indicando se o token é válido
     */

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

   private static final String JWT_COOKIE_NAME = "jwt_token"; // Ajuste para o nome do seu cookie se necessário

    /**
     * Extrai o token da requisição (Header ou Cookie) e valida sua assinatura e expiração.
     * 
     * @param request HttpServletRequest contendo o token
     * @return boolean indicando se o token é válido
     */
    public boolean isValidToken(HttpServletRequest request) {
        String token = extractToken(request);

        if (token == null || token.trim().isEmpty()) {
            return false;
        }

        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            
            // Faz o parse e verifica a assinatura do token JWT extraído
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
                
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Captura qualquer falha: expiração, assinatura inválida, token corrompido ou mal formatado
            return false;
        }
    }

    /**
     * Método auxiliar privado responsável por isolar a lógica de extração do token.
     */
    private String extractToken(HttpServletRequest request) {
        // 1. Tenta extrair do Header Authorization (Bearer Token)
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // 2. Se não estiver no Header, tenta procurar no Cookie HttpOnly
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (JWT_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }



    public void registrar(RegisterDTO dto) {
        // if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
        //     throw new RuntimeException("E-mail já cadastrado!");
        // }

        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario novoUsuario = new Usuario(null, dto.nome(), dto.email(), senhaCriptografada);
        usuarioRepository.save(novoUsuario);
    }

    public TokenDTO login(LoginDTO dto) {
     
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // O principal será o e-mail ou o objeto do usuário autenticado
        String token = tokenService.gerarToken(dto.email());
        return new TokenDTO(token);
    }
}
