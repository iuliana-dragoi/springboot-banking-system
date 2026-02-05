package com.example.bankingsystem.account.service;

import com.example.bankingsystem.account.dto.crud.BulkCreateAccountsRequest;
import com.example.bankingsystem.account.dto.crud.CreateAccountRequest;
import com.example.bankingsystem.account.dto.crud.UpdateAccountRequest;
import java.math.BigDecimal;

public interface AccountService extends AccountSearchService {

    void createAccount(CreateAccountRequest request);

    void createAccounts(BulkCreateAccountsRequest requests);

    void updateAccount(UpdateAccountRequest request);

    void deleteAccount(Long id);

    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(String accountNumber, BigDecimal amount);

    void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount);

    void changeStatusToClosed(Long accountId);

}
