package com.example.bankingsystem.loan;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class LoanApplication {

    private final String applicantName;
    private final String applicantEmail;
    private final BigDecimal amount;
    private final int termMonths;
    private final BigDecimal interestRate;
    private final LocalDate applicationDate;
    private final boolean approved;

    private LoanApplication(Builder builder) {
        this.applicantName = builder.applicantName;
        this.applicantEmail = builder.applicantEmail;
        this.amount = builder.amount;
        this.termMonths = builder.termMonths;
        this.interestRate = builder.interestRate;
        this.applicationDate = builder.applicationDate;
        this.approved = builder.approved;
    }

    public static class Builder {
        private final String applicantName;
        private final String applicantEmail;
        private final BigDecimal amount;

        private int termMonths = 12;
        private BigDecimal interestRate = BigDecimal.valueOf(5.0);
        private LocalDate applicationDate = LocalDate.now();
        private boolean approved = false;

        public Builder(String applicantName, String applicantEmail, BigDecimal amount) {
            this.applicantName = applicantName;
            this.applicantEmail = applicantEmail;
            this.amount = amount;
        }

        public Builder termMonths(int termMonths) {
            this.termMonths = termMonths;
            return this;
        }

        public Builder interestRate(BigDecimal interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public Builder applicationDate(LocalDate applicationDate) {
            this.applicationDate = applicationDate;
            return this;
        }

        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public LoanApplication build() {
            return new LoanApplication(this);
        }
    }
}
