package com.example.gastos_fianceiros.infrastructure.repository;



import com.example.gastos_fianceiros.DTO.ViewResumoFinanceiroDTO;
import com.example.gastos_fianceiros.domain.model.Gasto; // ou qualquer entidade base
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioFinanceiroRepository extends JpaRepository<Gasto, Long> {

    @Query(value = """
        SELECT 
            mes,
            COALESCE(SUM(CASE WHEN tipo = 'RECEITA' THEN total ELSE 0 END), 0) AS receitas,
            COALESCE(SUM(CASE WHEN tipo = 'DESPESA' THEN total ELSE 0 END), 0) AS despesas
        FROM (
            SELECT TO_CHAR(r.data, 'YYYY-MM') AS mes, 'RECEITA' AS tipo, r.valor AS total FROM receitas r
            UNION ALL
            SELECT TO_CHAR(g.data_gasto, 'YYYY-MM') AS mes, 'DESPESA' AS tipo, g.valor AS total FROM gastos g
        ) t
        GROUP BY mes
        ORDER BY mes ASC
        """, nativeQuery = true)
    List<ViewResumoFinanceiroDTO> obterReceitasDespesasPorMes();
}
