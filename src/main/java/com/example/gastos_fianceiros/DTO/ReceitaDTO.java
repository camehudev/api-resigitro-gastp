package com.example.gastos_fianceiros.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class ReceitaDTO {

    private Long id;
    private String categoria;
    private String descricao;
    private BigDecimal valor;
    private Date data;

    // Construtor vazio padrão
    public ReceitaDTO() {
    }

    // <-- ADICIONE ESTE CONSTRUTOR PARA RESOLVER O ERRO IMEDIATAMENTE -->
    public ReceitaDTO(String categoria,String descricao, BigDecimal valor, Date data) {
        this.categoria= categoria;
        this.descricao= descricao;
        this.valor = valor;
        this.data = data;
    }

    // Construtor completo com ID
    public ReceitaDTO(Long id,String categoria,String descricao, BigDecimal valor, Date data) {
        this.id = id;
        this.categoria = categoria;
        this.descricao= descricao;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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