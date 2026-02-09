package com.example.bankingsystem.bank.country.US;

import com.example.bankingsystem.bank.AccountValidator;

public class UsAccountValidator implements AccountValidator {

    @Override
    public boolean validate(String accountNumber) {
        return accountNumber.matches("\\d{9}");
    }
}
