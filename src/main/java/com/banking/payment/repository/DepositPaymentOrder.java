package com.banking.payment.repository;

import java.math.BigDecimal;
import java.util.UUID;

public class DepositPaymentOrder extends AbstractPaymentOrder {

    DepositPaymentOrder() {
        super();
    }

    DepositPaymentOrder(UUID beneficiaryAccountId, BigDecimal amount) {
        super();
        this.beneficiaryAccountId = beneficiaryAccountId;
        this.amount = amount;
    }
}
