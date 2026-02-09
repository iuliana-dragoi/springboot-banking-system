package com.example.bankingsystem.bank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OnboardAccountRequest(

        @NotNull
        @NotEmpty
        String country,

        @NotNull
        @NotEmpty
        String accountNumber) {
}
