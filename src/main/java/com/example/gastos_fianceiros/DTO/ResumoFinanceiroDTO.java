package com.example.gastos_fianceiros.DTO;


import java.math.BigDecimal;

public record ResumoFinanceiroDTO(
    BigDecimal totalReceitas,
    BigDecimal totalGastos,
    BigDecimal saldo
) {}
