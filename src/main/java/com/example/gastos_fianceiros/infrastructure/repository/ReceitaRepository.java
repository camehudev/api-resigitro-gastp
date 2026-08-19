package com.example.gastos_fianceiros.infrastructure.repository;

import com.example.gastos_fianceiros.DTO.ReceitaDTO;
import com.example.gastos_fianceiros.domain.model.Receitas;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receitas, Long> {
    // O JpaRepository já nos dá tudo pronto!
    List<Receitas> findAllByOrderByIdDesc();

    // Novo método para somar o valor total geral de todas as despesas
        @Query("SELECT SUM(g.valor) FROM Receitas g")
        BigDecimal somarValorTotalGeral();
}
