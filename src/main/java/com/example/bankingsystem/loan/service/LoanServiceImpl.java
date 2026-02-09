package com.example.bankingsystem.loan.service;

import com.example.bankingsystem.loan.Loan;
import com.example.bankingsystem.loan.LoanApplication;
import com.example.bankingsystem.loan.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional
    @Override
    public LoanApplication createLoanApplication(String name, String email, BigDecimal amount) {
        LoanApplication loanApplication = new LoanApplication.Builder(name, email, amount)
                .termMonths(24)
                .interestRate(BigDecimal.valueOf(4.5))
                .applicationDate(LocalDate.now())
                .approved(false)
                .build();

        Loan loan = Loan.builder()
                .applicantName(loanApplication.getApplicantName())
                .applicantEmail(loanApplication.getApplicantEmail())
                .amount(loanApplication.getAmount())
                .termMonths(loanApplication.getTermMonths())
                .interestRate(loanApplication.getInterestRate())
                .applicationDate(loanApplication.getApplicationDate())
                .approved(loanApplication.isApproved())
                .build();

        loanRepository.save(loan);

        return loanApplication;
    }
}
