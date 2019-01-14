package com.banking.payment.service;

import com.banking.account.exception.AccountNotFoundException;
import com.banking.account.repository.AccountRepository;
import com.banking.payment.exception.InsufficientBalanceException;
import com.banking.payment.exception.InvalidPaymentAmountException;
import com.banking.payment.repository.PaymentOrder;
import com.banking.payment.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final AccountRepository accountRepository;

    public PaymentService(PaymentRepository paymentRepository, AccountRepository accountRepository) {

        this.paymentRepository = paymentRepository;
        this.accountRepository = accountRepository;
    }

    public UUID acceptPaymentOrder(PaymentOrder paymentOrder) {
        return paymentRepository.acceptPaymentOrder(paymentOrder);
    }

    public void processPaymentOrder(PaymentOrder paymentOrder) {
        if (validPayeeAndBeneficiaryAccountsExistFor(paymentOrder) && payeeAccountHasSufficientBalanceFor(paymentOrder) && paymentAmountIsValid(paymentOrder)) {

        }
    }

    private boolean paymentAmountIsValid(PaymentOrder payment) {

        if (payment.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPaymentAmountException("Amount should be non-negative");
        }
        return true;
    }

    private boolean payeeAccountHasSufficientBalanceFor(PaymentOrder payment) {
        if (accountRepository.getAccountBalance(payment.getPayeeAccountId()).compareTo(payment.getAmount()) < 0) {
            throw new InsufficientBalanceException(payment.getPayeeAccountId());
        }
        return true;
    }

    private boolean validPayeeAndBeneficiaryAccountsExistFor(PaymentOrder payment) {

        if (!accountRepository.isAccountPresent(payment.getBeneficiaryAccountId())) {
            throw new AccountNotFoundException(payment.getBeneficiaryAccountId());
        }
        if (!accountRepository.isAccountPresent(payment.getPayeeAccountId())) {
            throw new AccountNotFoundException(payment.getPayeeAccountId());

        }
        return true;

    }
}
