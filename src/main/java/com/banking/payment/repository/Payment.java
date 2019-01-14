package com.banking.payment.repository;

public class Payment extends FundTransferPaymentOrder {

    private String message="";

    private PaymentStatus status;


    public Payment(FundTransferPaymentOrder paymentOrder, String message, PaymentStatus status) {
        super(paymentOrder.getPayeeAccountId(), paymentOrder.getBeneficiaryAccountId(), paymentOrder.getAmount());
        this.message = message;
        this.status = status;
    }

    public Payment(DepositPaymentOrder paymentOrder, String message, PaymentStatus status) {
        super(null, paymentOrder.getBeneficiaryAccountId(), paymentOrder.getAmount());
        this.message = message;
        this.status = status;
    }

    public void updateState(String message, PaymentStatus status) {
        this.message+=(" - "+message);
        this.status=status;
    }

    public String getMessage() {
        return message;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
