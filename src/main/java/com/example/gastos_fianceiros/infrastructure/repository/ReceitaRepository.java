package com.example.gastos_fianceiros.infrastructure.repository;

import com.example.gastos_fianceiros.domain.model.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receitas, Long> {
    // O JpaRepository já nos dá tudo pronto!
}
