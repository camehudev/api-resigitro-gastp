package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.LoginDTO;
import com.example.gastos_fianceiros.DTO.RegisterDTO;
import com.example.gastos_fianceiros.DTO.TokenDTO;
import com.example.gastos_fianceiros.application.service.AutenticacaoService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/check-session")
    public ResponseEntity<Boolean> checkSession(HttpServletRequest request) {
        boolean isValid = autenticacaoService.isCookieSessionValid(request);
        
        if (isValid) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
}
