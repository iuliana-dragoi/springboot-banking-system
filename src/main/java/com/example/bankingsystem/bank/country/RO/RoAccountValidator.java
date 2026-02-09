package com.example.bankingsystem.bank.country.RO;

import com.example.bankingsystem.bank.AccountValidator;

public class RoAccountValidator implements AccountValidator {

    @Override
    public boolean validate(String accountNumber) {
        return accountNumber.startsWith("RO") && accountNumber.length() == 24;
    }
}
