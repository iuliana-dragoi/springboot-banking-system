package com.example.bankingsystem.account.controller;

import com.example.bankingsystem.account.dto.search.AccountSearchRequest;
import com.example.bankingsystem.account.dto.search.AccountSearchResponse;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import com.example.bankingsystem.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountSearchController {

    public final AccountService accountService;

    public AccountSearchController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/search")
    public ResponseEntity<List<AccountSearchResponse>> searchAccounts(@ModelAttribute @Valid AccountSearchRequest request) {
        List<AccountSearchResponse> result = accountService.searchAccounts(request);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/search/paged")
    public ResponseEntity<Page<AccountSearchResponse>> searchAccountsWithPagination(
            @ModelAttribute @Valid AccountSearchRequest request,
            @PageableDefault(size = 20, sort = "balance", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AccountSearchResponse> result = accountService.searchAccountsWithPagination(request, pageable);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/search/byStatusAndType")
    public ResponseEntity<Page<AccountSearchProjection>> searchByStatusAndType(@RequestParam AccountStatus status, @RequestParam AccountType type, Pageable pageable) {
        Page<AccountSearchProjection> result = accountService.searchByStatusAndType(status, type, pageable);
        return ResponseEntity.ok(result);
    }

}
