package com.example.bankingsystem.account.service;

import com.example.bankingsystem.account.mapper.AccountMapper;
import com.example.bankingsystem.account.mapper.AccountSearchMapper;
import com.example.bankingsystem.account.mapper.AccountSpecificationBuilder;
import com.example.bankingsystem.account.model.*;
import com.example.bankingsystem.account.factory.AccountFactory;
import com.example.bankingsystem.account.dto.AccountSearchCriteria;
import com.example.bankingsystem.account.repository.AccountRepository;
import com.example.bankingsystem.account.dto.AccountSearchRequest;
import com.example.bankingsystem.account.dto.AccountSearchResponse;
import com.example.bankingsystem.account.dto.crud.CreateAccountRequest;
import com.example.bankingsystem.account.dto.crud.UpdateAccountRequest;
import com.example.bankingsystem.account.exception.AccountNotFoundException;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void createAccount(CreateAccountRequest request) {
        Address address = new Address(request.street(), request.city(), request.postalCode(), request.country());
        Owner owner = new Owner(request.ownerFirstName(), request.ownerLastName(), request.ownerEmail(), address);
        Account account = AccountFactory.createAccount(request.type(), request.status(), request.balance(), owner);
        repository.save(account);
    }

    @Transactional
    @Override
    public void updateAccount(UpdateAccountRequest request) {
        Account account = repository.findForUpdateByAccountNumber(request.accountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        Owner owner = account.getOwner();
        Address address = owner.getAddress();

        applyIfNotNull(request.ownerFirstName(), owner::setFirstName);
        applyIfNotNull(request.ownerLastName(), owner::setLastName);
        applyIfNotNull(request.ownerEmail(), owner::setEmail);

        applyIfNotNull(request.street(), address::setStreet);
        applyIfNotNull(request.city(), address::setCity);
        applyIfNotNull(request.postalCode(), address::setPostalCode);
        applyIfNotNull(request.country(), address::setCountry);

        applyIfNotNull(request.balance(), account::setBalance);
        applyIfNotNull(request.status(), account::setStatus);
    }

    @Transactional
    @Override
    public void deleteAccount(Long id) {
        Account account = repository.findForUpdateByAccountNumber(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        repository.delete(account);
    }

    @Override
    public List<AccountSearchResponse> searchAccounts(AccountSearchRequest request) {
        AccountSearchCriteria criteria = AccountSearchMapper.toCriteria(request);
        return repository.findAccounts(criteria).stream().map(AccountMapper::toDto).toList();
    }

    @Override
    public List<Account> searchAccountsWithSpecification(AccountSearchRequest request) {
        Specification<Account> spec = AccountSpecificationBuilder.build(request);
        return repository.findAll(spec);
    }

    @Override
    public Page<AccountSearchResponse> searchAccountsWithPagination(AccountSearchRequest request, Pageable pageable) {
        Specification<Account> spec = AccountSpecificationBuilder.build(request);
        Page<Account> result = repository.findAll(spec, pageable);
        return AccountMapper.toDto(result);
    }

    @Override
    public Page<AccountSearchProjection> searchByStatusAndType(AccountStatus status, AccountType type, Pageable pageable) {
        return repository.findAllByStatusAndType(status, type, pageable);
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

    private static<T> void applyIfNotNull(T value, Consumer<T> setter) {
        if(value != null) {
            setter.accept(value);
        }
    }
}
