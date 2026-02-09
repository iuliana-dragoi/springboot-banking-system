package com.example.bankingsystem.loan.config;

import com.example.bankingsystem.transaction.config.TransactionIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfig {

    @Bean
    public TransactionIdGenerator idGenerator() {
        return TransactionIdGenerator.INSTANCE;
    }
}
