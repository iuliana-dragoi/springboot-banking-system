package com.example.bankingsystem.account.controller;

import com.example.bankingsystem.account.dto.crud.BulkCreateAccountsRequest;
import com.example.bankingsystem.account.dto.crud.CreateAccountRequest;
import com.example.bankingsystem.account.dto.crud.UpdateAccountRequest;
import com.example.bankingsystem.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountCrudController {

    public final AccountService accountService;

    public AccountCrudController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountRequest request) {
        accountService.createAccount(request);
        return ResponseEntity.status(201).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create/bulk")
    public ResponseEntity<?> createBulk(@RequestBody @Valid BulkCreateAccountsRequest requests) {
        accountService.createAccounts(requests);
        return ResponseEntity.ok(Map.of("createdCount", requests.getRequests().size()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateAccountRequest request) {
        accountService.updateAccount(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
