package com.example.bankingsystem.account.model;

import com.example.bankingsystem.account.exception.InvalidAmountException;
import com.example.bankingsystem.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "account",
    indexes = {
        @Index(name = "idx_account_status", columnList = "status"),
        @Index(name = "idx_account_balance", columnList = "balance"),
        @Index(name = "idx_account_type", columnList = "type")
    }
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Account extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    public abstract AccountType getType();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="firstName", column=@Column(name="owner_first_name", nullable=false)),
            @AttributeOverride(name="lastName", column=@Column(name="owner_last_name", nullable=false)),
            @AttributeOverride(name="email", column=@Column(name="owner_email", nullable=false)),
    })
    private Owner owner;

    public void deposit(BigDecimal amount) {
        if(amount == null || amount.intValue() < 0)
            throw new InvalidAmountException("Invalid number!");

        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if(amount.compareTo(balance) > 0)
            throw new InvalidAmountException("Balance cannot be negative!");

        balance = balance.subtract(amount);
    }
}
