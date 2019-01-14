package com.banking.payment.repository;

import java.math.BigDecimal;
import java.util.UUID;

public class FundTransferPaymentOrder extends AbstractPaymentOrder {

    private UUID payeeAccountId;

    @Override
    public String toString() {
        return "FundTransferPaymentOrder{" +
                "id=" + id +
                ", payeeAccountId=" + payeeAccountId +
                ", beneficiaryAccountId=" + beneficiaryAccountId +
                ", amount=" + amount +
                '}';
    }

    FundTransferPaymentOrder() {
        super();
    }

    FundTransferPaymentOrder(UUID payeeAccountId, UUID beneficiaryAccountId, BigDecimal amount) {
        super();
        this.payeeAccountId = payeeAccountId;
        this.beneficiaryAccountId = beneficiaryAccountId;
        this.amount = amount;
    }

    public UUID getPayeeAccountId() {
        return payeeAccountId;
    }



}
