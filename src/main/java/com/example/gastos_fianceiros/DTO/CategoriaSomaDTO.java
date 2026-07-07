package com.example.gastos_fianceiros.DTO;

import java.math.BigDecimal;

public record CategoriaSomaDTO(
    String categoria, 
    BigDecimal total) 
    { }