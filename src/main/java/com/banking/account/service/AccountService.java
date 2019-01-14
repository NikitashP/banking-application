package com.banking.account.service;

import com.banking.account.exception.AccountNotFoundException;
import com.banking.account.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.UUID;


public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UUID createAccount() {
        return accountRepository.createAccount();
    }

    public BigDecimal getAccountBalance(UUID accountId) {
        if (!accountRepository.isAccountPresent(accountId)) {
            throw new AccountNotFoundException(accountId);
        }
        return accountRepository.getAccountBalance(accountId);
    }
}
