package com.example.bankingsystem.account.service;

import com.example.bankingsystem.account.dto.AccountSearchRequest;
import com.example.bankingsystem.account.dto.AccountSearchResponse;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> searchAccounts(AccountSearchRequest request);

    List<Account> searchAccountsWithSpecification(AccountSearchRequest request);

    Page<AccountSearchResponse> searchAccountsWithPagination(AccountSearchRequest request, Pageable pageable);

    Page<AccountSearchProjection> searchByStatusAndType(AccountStatus status, AccountType type, Pageable pageable);

    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(String accountNumber, BigDecimal amount);

    void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount);

}
