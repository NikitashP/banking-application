package com.banking.payment.repository;


import com.banking.payment.service.PaymentService;

public class PaymentOrderProcessor extends Thread {

    private final PaymentService paymentService;

    private final PaymentOrderQueue paymentOrderQueue;

    public PaymentOrderProcessor(PaymentService paymentService, PaymentOrderQueue paymentOrderQueue) {
        this.paymentService = paymentService;
        this.paymentOrderQueue = paymentOrderQueue;
    }

    @Override
    public void run() {
        for (; ; ) {
            PaymentOrder paymentOrder = null;
            paymentOrder = paymentOrderQueue.getPaymentOrderFromQueue();
            paymentService.processPaymentOrder(paymentOrder);
        }
    }
}