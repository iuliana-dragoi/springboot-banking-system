package com.example.bankingsystem.account.mapper;

import com.example.bankingsystem.account.dto.crud.CreateAccountRequest;
import com.example.bankingsystem.account.factory.AccountFactory;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.model.Address;
import com.example.bankingsystem.account.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestMapper {

    private final AccountFactory accountFactory;

    public AccountRequestMapper(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    public Account toAccount(CreateAccountRequest request) {
        Address address = new Address(request.street(), request.city(), request.postalCode(), request.country());
        Owner owner = new Owner(request.ownerFirstName(), request.ownerLastName(), request.ownerEmail(), address);
        return accountFactory.createAccount(request.type(), request.status(), request.balance(), owner);
    }
}
