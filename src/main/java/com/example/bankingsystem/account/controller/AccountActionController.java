package com.example.bankingsystem.account.controller;

import com.example.bankingsystem.account.dto.actions.DepositRequest;
import com.example.bankingsystem.account.dto.actions.TransferRequest;
import com.example.bankingsystem.account.dto.actions.WithdrawRequest;
import com.example.bankingsystem.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountActionController {

    public final AccountService accountService;

    public AccountActionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody @Valid DepositRequest request) {
        accountService.deposit(request.accountNumber(), request.amount());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody @Valid WithdrawRequest request) {
        accountService.withdraw(request.accountNumber(), request.amount());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody @Valid TransferRequest request) {
        accountService.transfer(request.fromAccount(), request.toAccount(), request.amount());
        return ResponseEntity.noContent().build();
    }
}
