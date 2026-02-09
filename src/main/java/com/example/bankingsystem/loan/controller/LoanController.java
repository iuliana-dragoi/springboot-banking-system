package com.example.bankingsystem.loan.controller;

import com.example.bankingsystem.loan.LoanApplication;
import com.example.bankingsystem.loan.model.ApplyLoanRequest;
import com.example.bankingsystem.loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan/")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanApplication> applyForLoan(@Valid @RequestBody ApplyLoanRequest request) {
        LoanApplication application = loanService.createLoanApplication(request.name(), request.email(), request.amount());
        return ResponseEntity.ok(application);
    }
}
