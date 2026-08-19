package com.example.gastos_fianceiros.controller;

import com.example.gastos_fianceiros.DTO.ResumoFinanceiroDTO;
import com.example.gastos_fianceiros.application.service.ResumoFinanceiroService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saldo")
public class ResumoFinanceiroController {

    private final ResumoFinanceiroService resumoService;

    public ResumoFinanceiroController(ResumoFinanceiroService resumoService) {
        this.resumoService = resumoService;
    }

    @GetMapping
    public ResponseEntity<ResumoFinanceiroDTO> obterResumo() {
        ResumoFinanceiroDTO resumo = resumoService.obterResumoFinanceiro();
        return ResponseEntity.ok(resumo);
    }
}
