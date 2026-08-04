package com.example.gastos_fianceiros.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class ReceitaDTO {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Date data;

    // Construtor vazio padrão
    public ReceitaDTO() {
    }

    // <-- ADICIONE ESTE CONSTRUTOR PARA RESOLVER O ERRO IMEDIATAMENTE -->
    public ReceitaDTO(String nome, BigDecimal valor, Date data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    // Construtor completo com ID
    public ReceitaDTO(Long id, String nome, BigDecimal valor, Date data) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}