package com.example.gastos_fianceiros.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public record GastoDTO(
    Long id,
    String categoria,
    BigDecimal valor,
    String descricao,
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate data_gasto,
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate data_criacao,
    
    Integer total_parcelas,
    Integer parcela_atual
) {

    }