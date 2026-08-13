package com.example.gastos_fianceiros.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "categoria", nullable = false, length = 255)
    private String categoria;

    @Column(name = "valor", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_gasto")
    private LocalDate dataGasto;

    // Usando acento no nome da coluna exatamente como você pediu
    @Column(name = "data_criação")
    private LocalDate dataCriacao;

    @Column(name = "total_parcelas", nullable = false)
    private int totalParcelas;

    @Column(name = "parcela_atual", nullable = false)
    private int parcelaAtual;

    // Construtores, Getters e Setters
    public Gasto(String string, BigDecimal bigDecimal,String descricao, Object object, LocalDate localDate, Integer integer, Integer integer2) {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getDescvricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataGasto() { return dataGasto; }
    public void setDataGasto(LocalDate dataGasto) { this.dataGasto = dataGasto; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public int getTotalParcelas() { return totalParcelas; }
    public void setTotalParcelas(int totalParcelas) { this.totalParcelas = totalParcelas; }

    public int getParcelaAtual() { return parcelaAtual; }
    public void setParcelaAtual(int parcelaAtual) { this.parcelaAtual = parcelaAtual; }
}