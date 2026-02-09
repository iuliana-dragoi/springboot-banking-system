package com.example.bankingsystem.bank.controller;

import com.example.bankingsystem.bank.dto.ReportTransactionRequest;
import com.example.bankingsystem.bank.dto.TransferRequest;
import com.example.bankingsystem.bank.dto.OnboardAccountRequest;
import com.example.bankingsystem.bank.service.BankService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/onboard")
    public ResponseEntity<String> onboardAccount(@Valid @RequestBody OnboardAccountRequest request) {
        bankService.onboardAccount(request.country(), request.accountNumber());
        return ResponseEntity.ok("Account onboarded for " + request.country());
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest request) {
        bankService.transfer(request.country(), request.from(), request.to(), request.amount());
        return ResponseEntity.ok("Transfer completed for " + request.country());
    }

    @PostMapping
    public ResponseEntity<String> reportTransaction(@Valid @RequestBody ReportTransactionRequest request) {
        bankService.reportTransaction(request.country(), request.transactionId(), request.amount());
        return ResponseEntity.ok("Transaction reported for " + request.country());
    }
}
