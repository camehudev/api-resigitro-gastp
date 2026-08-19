package com.example.gastos_fianceiros.infrastructure.repository;

import com.example.gastos_fianceiros.DTO.CategoriaSomaDTO;
import com.example.gastos_fianceiros.DTO.GastoDTO;
import com.example.gastos_fianceiros.domain.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale.Category;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
     @Query("SELECT new com.example.gastos_fianceiros.DTO.CategoriaSomaDTO(UPPER(g.categoria), SUM(g.valor)) " +
       "FROM Gasto g GROUP BY UPPER(g.categoria)")

      List<CategoriaSomaDTO> somarValoresPorCategoria();
      
      List<Gasto> findAllByOrderByIdDesc();

      // Novo método para somar o valor total geral de todas as despesas
        @Query("SELECT SUM(g.valor) FROM Gasto g")
        BigDecimal somarValorTotalGeral();
}

