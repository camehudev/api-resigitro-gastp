package com.example.gastos_fianceiros.application.service;

import com.example.gastos_fianceiros.DTO.LoginDTO;
import com.example.gastos_fianceiros.DTO.RegisterDTO;
import com.example.gastos_fianceiros.DTO.TokenDTO;
import com.example.gastos_fianceiros.domain.model.Usuario;
import com.example.gastos_fianceiros.infrastructure.repository.UsuarioRepository;
import com.example.gastos_fianceiros.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
