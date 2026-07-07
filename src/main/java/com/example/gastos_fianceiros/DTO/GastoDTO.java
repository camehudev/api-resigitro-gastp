package com.example.gastos_fianceiros.DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record GastoDTO(
    String categoria,
    String valor,
    String descricao,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate data)
     { }
