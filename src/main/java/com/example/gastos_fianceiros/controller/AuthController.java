package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.LoginDTO;
import com.example.gastos_fianceiros.DTO.RegisterDTO;
import com.example.gastos_fianceiros.DTO.TokenDTO;
import com.example.gastos_fianceiros.application.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO dto) {
        autenticacaoService.registrar(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto) {
        TokenDTO tokenDTO = autenticacaoService.login(dto);
        return ResponseEntity.ok(tokenDTO);
    }
}
