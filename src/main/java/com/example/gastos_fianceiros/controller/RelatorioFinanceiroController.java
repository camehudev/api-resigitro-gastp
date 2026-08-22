package com.example.gastos_fianceiros.controller;


import com.example.gastos_fianceiros.DTO.ViewResumoFinanceiroDTO;
import com.example.gastos_fianceiros.application.service.RelatorioFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioFinanceiroController {

    @Autowired
    private RelatorioFinanceiroService service;

    @GetMapping("/receitas-despesas-mes")
    public ResponseEntity<List<ViewResumoFinanceiroDTO>> listarReceitasDespesasPorMes() {
        List<ViewResumoFinanceiroDTO> resumo = service.obterReceitasDespesasPorMes();
        return ResponseEntity.ok(resumo);
    }
}
