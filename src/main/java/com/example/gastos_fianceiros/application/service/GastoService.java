package com.example.gastos_fianceiros.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gastos_fianceiros.DTO.CategoriaSomaDTO;
import com.example.gastos_fianceiros.DTO.ResumoMensalCategoriaDTO;
import com.example.gastos_fianceiros.domain.model.Gasto;
import com.example.gastos_fianceiros.infrastructure.repository.GastoRepository;

@Service
public class GastoService {

    @Autowired
    private final GastoRepository repository;


    public List<CategoriaSomaDTO> obterResumoPorCategoria() {

        return repository.somarValoresPorCategoria();
    }

    // Injeção via construtor (prática recomendada pelo Spring)
    public GastoService(GastoRepository repository) {
        this.repository = repository;
    }

    public void salvarGasto(Gasto gasto) {
        repository.save(gasto);
    }

    public BigDecimal obterValorTotalGeral() {
        BigDecimal total = repository.somarValorTotalGeral();
        // Evita retornar null para o frontend caso a tabela esteja vazia
        return total != null ? total : BigDecimal.ZERO;
    }


    public List<ResumoMensalCategoriaDTO> obterResumoMensalPorCategoria() {
        // Aqui você pode adicionar regras de negócio adicionais se necessário 
        // antes de retornar os dados consolidados do repositório.
        return repository.obterResumoMensalPorCategoria();
    }

}