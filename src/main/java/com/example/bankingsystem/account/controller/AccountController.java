package com.example.bankingsystem.account.controller;

import com.example.bankingsystem.account.dto.AccountSearchRequest;
import com.example.bankingsystem.account.dto.AccountSearchResponse;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/search")
    public Page<AccountSearchResponse> search(AccountSearchRequest request, @PageableDefault(size = 20, sort = "balance", direction = Sort.Direction.DESC) Pageable pageable) {
        return accountService.searchAccountsWithPagination(request, pageable);
    }

}
