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
    private LocalDate data;

    // Adicione este construtor
    protected Gasto() { }


    public Gasto(String categoria, String valor, String descricao, LocalDate data) {
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    // GETTERS E SETTERS SÃO OBRIGATÓRIOS 
    public Long getId() { return id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}