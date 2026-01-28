package com.example.bankingsystem.account.dto.crud;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateAccountRequest(

        @NotNull
        String accountNumber,

        @NotNull
        AccountType type,

        @NotNull
        AccountStatus status,

        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal balance,

        @NotNull
        String ownerFirstName,

        @NotNull
        String ownerLastName,

        @NotNull
        @Email(message = "Invalid email format")
        String ownerEmail,

        @NotNull
        String street,

        @NotNull
        String city,

        @NotNull
        String postalCode,

        @NotNull
        String country
) {
}
