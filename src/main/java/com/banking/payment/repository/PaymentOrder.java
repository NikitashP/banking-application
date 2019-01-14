package com.banking.payment.repository;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentOrder {

    private UUID payeeAccountId;

    private UUID beneficiaryAccountId;

    private BigDecimal amount;

    PaymentOrder() {

    }

    PaymentOrder(UUID payeeAccountId, UUID beneficiaryAccountId, BigDecimal amount) {
        this.payeeAccountId = payeeAccountId;
        this.beneficiaryAccountId = beneficiaryAccountId;
        this.amount = amount;
    }

    public UUID getPayeeAccountId() {
        return payeeAccountId;
    }

    public UUID getBeneficiaryAccountId() {
        return beneficiaryAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
