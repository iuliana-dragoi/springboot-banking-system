package com.example.bankingsystem.account.repository.Custom;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.dto.AccountSearchCriteria;
import java.util.List;

public interface AccountRepositoryCustom {

    List<Account> findAccounts(AccountSearchCriteria searchCriteria);
}
