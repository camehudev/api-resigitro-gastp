package com.example.gastos_fianceiros.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    // Use BigDecimal para valores monetários para evitar erros de arredondamento
    @Column(nullable = false, precision = 19, scale = 2)
    private String valor;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    private LocalDate data_gasto;

    @Column(nullable = true)
    private LocalDate data_criacao;

    // Adicione este construtor
    protected Gasto() { }


    public Gasto(String categoria, String valor, String descricao, String data) {
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.data_gasto = data != null ? LocalDate.parse(data) : null;
        this.data_criacao = LocalDate.now(); // Define a data de criação como a data atual
    }

    // GETTERS E SETTERS SÃO OBRIGATÓRIOS 
    public Long getId() { return id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data_gasto; }
    public void setData(String data) { this.data_gasto = LocalDate.parse(data); }

    public LocalDate getDataCriacao() { return data_criacao; }
    public void setDataCriacao(LocalDate data_criacao) { this.data_criacao = data_criacao; }
}