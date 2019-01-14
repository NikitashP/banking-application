package com.banking.payment.exception;

import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(UUID uuid) {
        super("Insufficient Balance for account " + uuid.toString());
    }
}
