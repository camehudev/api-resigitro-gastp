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

    // CORRIGIDO: Valores monetários devem ser BigDecimal para precisão exata
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private int parcela_atual;

    @Column(nullable = false)
    private int total_parcelas;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    private LocalDate data_gasto;

    @Column(nullable = true)
    private LocalDate data_criacao;

    // Construtor padrão (obrigatório pelo JPA/Hibernate)
    public Gasto() {
    }

   public Gasto(String categoria, LocalDate dataGasto, BigDecimal valor, int totalParcelas, int parcelaAtual, String descricao) {
        this.categoria = categoria;
        this.data_gasto = dataGasto;
        this.valor = valor;
        this.total_parcelas = totalParcelas;
        this.parcela_atual = parcelaAtual;
        this.descricao = descricao;
        this.data_criacao = LocalDate.now(); // Gerado automaticamente, sem dependência do DTO/Controller
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public int getParcela_atual() { return parcela_atual; }
    public void setParcela_atual(int parcela_atual) { this.parcela_atual = parcela_atual; }

    public int getTotal_parcelas() { return total_parcelas; }
    public void setTotal_parcelas(int total_parcelas) { this.total_parcelas = total_parcelas; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData_gasto() { return data_gasto; }
    public void setData_gasto(LocalDate data_gasto) { this.data_gasto = data_gasto; }

    public LocalDate getData_criacao() { return data_criacao; }
    public void setData_criacao(LocalDate data_criacao) { this.data_criacao = data_criacao; }
}