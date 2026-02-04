package com.example.bankingsystem.account.events;

import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.service.AccountService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AccountEventListener {

    private final AccountService accountService;

    public AccountEventListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @EventListener
    public void handleAccountCreated(AccountCreatedEvent event) {
        System.out.println("Account created for: " + event.ownerEmail());

        AccountAction sendWelcomeEmail = e -> System.out.println("Sending welcome email to " + e.ownerEmail());
        AccountAction logAudit = e -> System.out.println("Logging audit for account " + e.accountId());
        AccountAction applyPremiumBenefits = e -> System.out.println("Applying premium benefits for " + e.ownerEmail());

        sendWelcomeEmail.execute(event);
        logAudit.execute(event);
        if(event.type() == AccountType.PREMIUM) {
            applyPremiumBenefits.execute(event);
        }
    }

    @Async("accountTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAccountStatusChanged(AccountStatusChangeEvent event) {
        AccountProcessor processor = accountService::changeStatusToClosed;
        processor.process(event.accountId());
    }
}
