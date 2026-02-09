package com.example.bankingsystem.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ReportTransactionRequest(

        @NotNull
        @NotEmpty
        String country,

        @NotNull
        @NotEmpty
        String transactionId,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal amount) {
}
