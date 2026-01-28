package com.example.bankingsystem.account.mapper;

import com.example.bankingsystem.account.dto.AccountSearchResponse;
import com.example.bankingsystem.account.model.Account;
import org.springframework.data.domain.Page;

public class AccountMapper {

    public static AccountSearchResponse toDto(Account account) {
        return new AccountSearchResponse(
                account.getStatus(),
                account.getAccountNumber(),
                account.getType(),
                account.getBalance(),
                account.getOwner().getFullName()
        );
    }

    public static Page<AccountSearchResponse> toDto(Page<Account> page) {
        return page.map(a -> new AccountSearchResponse(
                a.getStatus(),
                a.getAccountNumber(),
                a.getType(),
                a.getBalance(),
                a.getOwner().getFullName())
        );
    }
}
