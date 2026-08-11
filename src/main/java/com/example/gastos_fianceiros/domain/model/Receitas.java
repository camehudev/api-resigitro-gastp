package com.example.gastos_fianceiros.domain.model;

import java.math.BigDecimal; // Importante para valores monetários
import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receitas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // Coluna 1: Categoria
    @Column(name = "categoria", nullable = false)
    private String categoria;

    // Coluna 2: Descrição
    @Column(name = "descricao")
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor; // Alterado para BigDecimal e minúsculo (boa prática)

    @Column(nullable = false)
    private Date data;

    // Métodos extras foram removidos, o Lombok (@Getter e @Setter) cuida de tudo sozinho!
}