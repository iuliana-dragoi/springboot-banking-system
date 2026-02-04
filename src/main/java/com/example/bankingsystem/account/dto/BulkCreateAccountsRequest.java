package com.example.bankingsystem.account.dto;

import com.example.bankingsystem.account.dto.crud.CreateAccountRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BulkCreateAccountsRequest {

    @Size(max = 20)
    @Valid
    private List<CreateAccountRequest> requests;
}
