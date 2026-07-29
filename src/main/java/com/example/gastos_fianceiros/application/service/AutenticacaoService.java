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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    // Nome do cookie configurado no seu login (ex: "SESSION", "JWT", etc.)
    private static final String COOKIE_NAME = "SESSION";

    /**
     * Valida se o cookie de sessão HttpOnly está presente e é válido.
     * 
     * @param request Requisição HTTP recebida do cliente
     * @return boolean indicando se a sessão é válida
     */

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean isCookieSessionValid(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return false;
        }

        // Procura pelo cookie de autenticação na requisição
        for (Cookie cookie : request.getCookies()) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                String sessionValue = cookie.getValue();
                
                // Validação do token ou da sessão:
                // Se for um JWT ou ID de sessão, valide aqui (ex: tokenService.validate(sessionValue))
                return isValidTokenOrSession(sessionValue);
            }
        }

        return false;
    }

    private boolean isValidTokenOrSession(String sessionValue) {
        // Implemente sua regra de negócio de validação (banco de dados, cache Redis ou assinatura do token)
        // Exemplo simplificado: se a string não for vazia, considera válido
        return sessionValue != null && !sessionValue.trim().isEmpty();
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
