package com.example.bankingsystem.account.service;

import com.example.bankingsystem.account.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountPrototypeService {

    public Account cloneAccount(Account template) throws CloneNotSupportedException {
        return template.clone();
    }
}
