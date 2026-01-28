package com.example.bankingsystem.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerIdRequest(

    @NotNull
    @NotEmpty
    Long id
) {
}
