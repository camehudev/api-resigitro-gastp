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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    // 1. Cria o token com as credenciais brutas (Authenticated = false)
    var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
   
    // 2. O AuthenticationManager valida as credenciais. 
    // Se estiver tudo correto, 'auth' retorna preenchido e com Authenticated = true.
    // Se a senha estiver errada ou o e-mail não existir, ele lança BadCredentialsException automaticamente.
    var auth = this.authenticationManager.authenticate(usernamePassword);
     

    // 3. Opcional, mas recomendado: Define a autenticação no contexto atual do Spring Security
    SecurityContextHolder.getContext().setAuthentication(auth);

    // 4. Extrai o usuário autenticado de dentro do objeto 'auth' retornado
    var usuario = (UserDetails) auth.getPrincipal();


    // 5. Gera o token utilizando o e-mail ou username oficial recuperado do banco
    String token = tokenService.gerarToken(usuario.getUsername());    

    // 6. Retorna o DTO contendo o JWT
    return new TokenDTO(token);
}
}
