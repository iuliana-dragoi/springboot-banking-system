package com.example.bankingsystem.loan;

import com.example.bankingsystem.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan extends BaseEntity {

    private String applicantName;
    private String applicantEmail;
    private BigDecimal amount;
    private int termMonths;
    private BigDecimal interestRate;
    private LocalDate applicationDate;
    private boolean approved;
}
