package com.example.bankingsystem.account.service;

import com.example.bankingsystem.account.dto.AccountSearchRequest;
import com.example.bankingsystem.account.dto.AccountSearchResponse;
import com.example.bankingsystem.account.exception.AccountNotFoundException;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.dto.AccountSearchCriteria;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.repository.AccountRepository;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import com.example.bankingsystem.account.repository.Specification.AccountSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> searchAccounts(AccountSearchRequest request) {
        AccountSearchCriteria criteria = buildCriteria(request);
        return repository.findAccounts(criteria);
    }

    @Override
    public List<Account> searchAccountsWithSpecification(AccountSearchRequest request) {
        Specification<Account> spec = buildSpecification(request);
        return repository.findAll(spec);
    }

    @Override
    public Page<AccountSearchResponse> searchAccountsWithPagination(AccountSearchRequest request, Pageable pageable) {
        Specification<Account> spec = buildSpecification(request);
        Page<Account> result = repository.findAll(spec, pageable);
        return mapToPageDtoResponse(result);
    }

    @Override
    public Page<AccountSearchProjection> searchByStatusAndType(AccountStatus status, AccountType type, Pageable pageable) {
        return repository.findAllByStatusAndType(status, type, pageable);
    }

    private Specification<Account> buildSpecification(AccountSearchRequest request) {
        return Specification
                .where(AccountSpecification.hasStatus(request. getStatus()))
                .and(AccountSpecification.hasType(request. getAccountType()))
                .and(AccountSpecification.hasNumber(request. getAccountNumber()))
                .and(AccountSpecification.minBalance(request. getMinBalance()))
                .and(AccountSpecification.maxBalance(request. getMaxBalance()))
                .and(AccountSpecification.accountTypeEquals(request. getAccountType()));
    }

    private AccountSearchCriteria buildCriteria(AccountSearchRequest request) {
        return AccountSearchCriteria.builder()
                .status(request. getStatus())
                .accountNumber(request. getAccountNumber())
                .maxBalance(request. getMaxBalance())
                .minBalance(request. getMinBalance())
                .accountType(request. getAccountType())
                .build();
    }

    private Page<AccountSearchResponse> mapToPageDtoResponse(Page<Account> page) {
        return page.map(a -> new AccountSearchResponse(
                a.getStatus(),
                a.getAccountNumber(),
                a.getType(),
                a.getBalance(),
                a.getOwner().getFullName())
        );
    }

    @Transactional
    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = repository.findForUpdateByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account " + accountNumber +" not found!"));
        account.deposit(amount);
        log.info("Deposited {} to account {}", amount, accountNumber);
    }

    @Transactional
    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = repository.findForUpdateByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account " + accountNumber +" not found!"));
        account.withdraw(amount);
        log.info("Withdrew {} from account {}", amount, accountNumber);
    }

    @Transactional
    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account from = repository.findForUpdateByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account " + fromAccountNumber +" not found!"));

        Account to = repository.findForUpdateByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account " + toAccountNumber +" not found!"));

        from.withdraw(amount);
        to.deposit(amount);
        log.info("Transferred {} from {} to {}", amount, fromAccountNumber, toAccountNumber);
    }
}
