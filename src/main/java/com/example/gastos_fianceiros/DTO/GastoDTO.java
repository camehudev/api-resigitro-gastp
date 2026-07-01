package com.example.gastos_fianceiros.DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public record GastoDTO(
    String categoria,
    BigDecimal valor, 
    String descricao,     
    LocalDate data)
     {

}
