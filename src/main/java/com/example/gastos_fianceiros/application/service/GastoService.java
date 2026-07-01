package com.example.gastos_fianceiros.application.service;

import org.springframework.stereotype.Service;

import com.example.gastos_fianceiros.domain.model.Gasto;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;

@Service
public class GastoService {

    private final GastoRepository repository;

    // Injeção via construtor (prática recomendada pelo Spring)
    public GastoService(GastoRepository repository) {
        this.repository = repository;
    }

    public void salvarGasto(Gasto gasto) {
        repository.save(gasto);
    }
}