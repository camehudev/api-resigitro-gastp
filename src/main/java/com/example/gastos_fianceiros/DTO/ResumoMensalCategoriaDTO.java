package com.example.gastos_fianceiros.DTO;

import java.math.BigDecimal;

public record ResumoMensalCategoriaDTO(
    String mes,        // Ex: "2026-01" ou "Janeiro"
    String categoria,  // Ex: "Alimentação"
    BigDecimal total       // Ex: 450.00
) {}