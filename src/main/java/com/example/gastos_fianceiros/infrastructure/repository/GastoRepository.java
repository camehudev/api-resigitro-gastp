package com.example.gastos_fianceiros.infrastructure.repository;

import com.example.gastos_fianceiros.domain.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // O JpaRepository já nos fornece:
    // save(), findById(), findAll(), deleteById(), existsById(), etc.
    
    // Você pode adicionar métodos de busca customizados aqui futuramente.
    // Exemplo: List<Gasto> findByDataBetween(LocalDate inicio, LocalDate fim);
}