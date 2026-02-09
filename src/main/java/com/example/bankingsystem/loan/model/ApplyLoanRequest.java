package com.example.bankingsystem.loan.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ApplyLoanRequest(

        @NotEmpty
        String name,

        @NotEmpty
        String email,

        @NotNull
        BigDecimal amount) {
}
