package com.example.bankingsystem.account.model;

import com.example.bankingsystem.account.exception.InvalidAmountException;
import com.example.bankingsystem.common.model.BaseEntity;
import com.example.bankingsystem.account.util.AccountNumberGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
@Getter
public abstract class Account extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Setter
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    public abstract AccountType getType();

    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="firstName", column=@Column(name="owner_first_name", nullable=false)),
            @AttributeOverride(name="lastName", column=@Column(name="owner_last_name", nullable=false)),
            @AttributeOverride(name="email", column=@Column(name="owner_email", nullable=false)),
    })
    private Owner owner;

    public Account(AccountStatus status, BigDecimal balance, Owner owner) {
        this.accountNumber = AccountNumberGenerator.generate();
        this.status = status;
        this.balance = balance;
        this.owner = owner;
    }

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
