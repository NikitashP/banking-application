package com.banking.account.repository;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

    private final UUID id;

    private final BigDecimal balance;

    Account() {
        this.id = UUID.randomUUID();
        this.balance = BigDecimal.ZERO;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
