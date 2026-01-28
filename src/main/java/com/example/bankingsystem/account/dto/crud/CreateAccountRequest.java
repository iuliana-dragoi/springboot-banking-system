package com.example.bankingsystem.account.dto.crud;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest (

    @NotNull(message = "{account.create.type}")
    AccountType type,

    @NotNull(message = "{account.create.status}")
    AccountStatus status,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal balance,

    @NotNull(message = "{account.create.owner.firstName}")
    String ownerFirstName,

    @NotNull(message = "{account.create.owner.lastName}")
    String ownerLastName,

    @NotNull
    @Email(message = "{account.create.owner.email}")
    String ownerEmail,

    @NotNull(message = "{account.create.owner.street}")
    String street,

    @NotNull(message = "{account.create.owner.city}")
    String city,

    @NotNull(message = "{account.create.owner.postalCode}")
    String postalCode,

    @NotNull(message = "{account.create.owner.country}")
    String country
) {

}