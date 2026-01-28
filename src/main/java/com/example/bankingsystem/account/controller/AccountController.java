package com.example.bankingsystem.account.controller;

import com.example.bankingsystem.account.dto.AccountSearchRequest;
import com.example.bankingsystem.account.dto.AccountSearchResponse;
import com.example.bankingsystem.account.dto.actions.DepositRequest;
import com.example.bankingsystem.account.dto.actions.TransferRequest;
import com.example.bankingsystem.account.dto.actions.WithdrawRequest;
import com.example.bankingsystem.account.dto.crud.CreateAccountRequest;
import com.example.bankingsystem.account.dto.crud.UpdateAccountRequest;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import com.example.bankingsystem.account.service.AccountService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountRequest request) {
        accountService.createAccount(request);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateAccountRequest request) {
        accountService.updateAccount(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountSearchResponse>> searchAccounts(@ModelAttribute @Valid AccountSearchRequest request) {
        List<AccountSearchResponse> result = accountService.searchAccounts(request);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/search/paged")
    public ResponseEntity<Page<AccountSearchResponse>> searchAccountsWithPagination(
            @ModelAttribute @Valid AccountSearchRequest request,
            @PageableDefault(size = 20, sort = "balance", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AccountSearchResponse> result = accountService.searchAccountsWithPagination(request, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/byStatusAndType")
    public ResponseEntity<Page<AccountSearchProjection>> searchByStatusAndType(@RequestParam AccountStatus status, @RequestParam AccountType type, Pageable pageable) {
        Page<AccountSearchProjection> result = accountService.searchByStatusAndType(status, type, pageable);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody @Valid DepositRequest request) {
        accountService.deposit(request.accountNumber(), request.amount());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody @Valid WithdrawRequest request) {
        accountService.withdraw(request.accountNumber(), request.amount());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody @Valid TransferRequest request) {
        accountService.transfer(request.fromAccount(), request.toAccount(), request.amount());
        return ResponseEntity.noContent().build();
    }


}
