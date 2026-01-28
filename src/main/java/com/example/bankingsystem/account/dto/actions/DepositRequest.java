package com.example.bankingsystem.account.dto.actions;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequest(

        @NotNull
        String accountNumber,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal amount
) {
}
