package com.example.bankingsystem.account.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountGroup implements Balanceable {

    private final List<Balanceable> children = new ArrayList<>();

    void add(Balanceable b) {
        children.add(b);
    }

    @Override
    public BigDecimal balance() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balanceable b : children) {
            sum = sum.add(b.balance());
        }
        return sum;
    }
}
