package com.example.gastos_fianceiros.application.service;



import com.example.gastos_fianceiros.DTO.ViewResumoFinanceiroDTO;
import com.example.gastos_fianceiros.infrastructure.repository.RelatorioFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioFinanceiroService {

    @Autowired
    private RelatorioFinanceiroRepository repository;

    public List<ViewResumoFinanceiroDTO> obterReceitasDespesasPorMes() {
        return repository.obterReceitasDespesasPorMes();
    }
}