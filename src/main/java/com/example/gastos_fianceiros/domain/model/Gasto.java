package com.example.gastos_fianceiros.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos") // Define o nome da tabela no banco
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate descricao;

    @Column(nullable = false)
    private LocalDate data;

    // Construtores, Getters e Setters
    public Gasto() {}

    public Gasto(String categoria, BigDecimal valor, LocalDate descricao) {
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.data= data;
    }

    // Getters e Setters omitidos para brevidade...
}