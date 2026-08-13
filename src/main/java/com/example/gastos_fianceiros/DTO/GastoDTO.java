package com.example.gastos_fianceiros.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public record GastoDTO(
    String categoria,
    
    // CORRIGIDO: Alterado de String para BigDecimal para bater com a Entidade e o Banco
    BigDecimal valor,    
    Integer parcela_atual,
    Integer total_parcelas,
    String descricao,    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate data_gasto,    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate data_criacao
) {}