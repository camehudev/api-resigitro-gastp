package com.example.gastos_fianceiros.application.service;

import com.example.gastos_fianceiros.DTO.ResumoFinanceiroDTO;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;
import com.example.gastos_fianceiros.infrastructure.repository.ReceitaRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ResumoFinanceiroService {

    private final GastoRepository gastoRepository;
    private final ReceitaRepository receitaRepository;

    public ResumoFinanceiroService(GastoRepository gastoRepository, ReceitaRepository receitaRepository) {
        this.gastoRepository = gastoRepository;
        this.receitaRepository = receitaRepository;
    }

    public ResumoFinanceiroDTO obterResumoFinanceiro() {
        // Busca total de gastos (tratando null caso a tabela esteja vazia)
        BigDecimal totalGastos = gastoRepository.somarValorTotalGeral();
        if (totalGastos == null) {
            totalGastos = BigDecimal.ZERO;
        }

        // Busca total de receitas (tratando null)
        BigDecimal totalReceitas = receitaRepository.somarValorTotalGeral();
        if (totalReceitas == null) {
            totalReceitas = BigDecimal.ZERO;
        }

        // Calcula o saldo (Receitas - Gastos)
        BigDecimal saldo = totalReceitas.subtract(totalGastos);

        return new ResumoFinanceiroDTO(totalReceitas, totalGastos, saldo);
    }
}
