package com.banking.payment.repository;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository {

    private final Map<UUID, Payment> paymentsContainer = new ConcurrentHashMap<UUID, Payment>();

    private final PaymentOrderQueue paymentOrderQueue;

    public PaymentRepository(PaymentOrderQueue paymentOrderQueue) {
        this.paymentOrderQueue = paymentOrderQueue;
    }

    public UUID acceptPaymentOrder(PaymentOrder paymentOrder) {
        return paymentOrderQueue.putPaymentOrder(paymentOrder);
    }

    public void processPaymentOrder(PaymentOrder paymentOrder) {

    }
}
