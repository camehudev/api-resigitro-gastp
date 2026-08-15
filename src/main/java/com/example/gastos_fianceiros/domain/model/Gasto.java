package com.example.gastos_fianceiros.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
@Data
@NoArgsConstructor
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_gasto")
    private LocalDate dataGasto;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "total_parcelas", nullable = false)
    private Integer totalParcelas;

    @Column(name = "parcela_atual", nullable = false)
    private Integer parcelaAtual;

    // Construtor exato correspondente à chamada no Controller
    public Gasto(String categoria, BigDecimal valor, String descricao, LocalDate dataGasto, LocalDate dataCriacao, Integer totalParcelas, Integer parcelaAtual) {
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.dataGasto = dataGasto;
        this.dataCriacao = dataCriacao;
        this.totalParcelas = totalParcelas;
        this.parcelaAtual = parcelaAtual;
    }
}