package com.example.bankingsystem.transaction.model;

import java.math.BigDecimal;

public record FeeRule(String type, BigDecimal amount) {
}
