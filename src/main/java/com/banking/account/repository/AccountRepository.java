package com.banking.account.repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class AccountRepository {

    private final Map<UUID, Account> accountsContainer = new ConcurrentHashMap<UUID, Account>();

    public UUID createAccount() {
        Account account = new Account();
        accountsContainer.put(account.getId(), account);
        return account.getId();
    }

    public BigDecimal getAccountBalance(UUID accountId) {
        return accountsContainer.get(accountId).getBalance();
    }

    public boolean isAccountPresent(UUID accountID) {
        return accountsContainer.containsKey(accountID);
    }
}
