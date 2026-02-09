package com.example.bankingsystem.loan.service;

import com.example.bankingsystem.loan.LoanApplication;

import java.math.BigDecimal;

public interface LoanService {
    LoanApplication createLoanApplication(String name, String email, BigDecimal amount);
}
