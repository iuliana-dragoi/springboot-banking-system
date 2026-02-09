package com.example.bankingsystem.transaction.config;

import com.example.bankingsystem.transaction.model.FeeRule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FeeConfigurationCache {

    private final Map<String, FeeRule> feeRules = new ConcurrentHashMap<>();
    private Instant lastUpdated;

    @PostConstruct
    void init() {
        feeRules.putAll(loadFeesFromDatabase());
        this.lastUpdated = Instant.now();
    }

    public Optional<FeeRule> getFeeRule(String transactionType) {
        return Optional.ofNullable(feeRules.get(transactionType));
    }

    public synchronized void refresh() {
        feeRules.clear();
        feeRules.putAll(loadFeesFromDatabase());
        lastUpdated = Instant.now();
    }

    private Map<String, FeeRule> loadFeesFromDatabase() {
        return Map.of(
                "WIRE_TRANSFER", new FeeRule("WIRE_TRANSFER", new BigDecimal("5.00")),
                "ATM_WITHDRAWAL", new FeeRule("ATM_WITHDRAWAL", new BigDecimal("2.50"))
        );
    }

}

