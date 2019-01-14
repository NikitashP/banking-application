package com.banking.payment.repository;

import com.banking.payment.exception.UnableToProcessPaymentOrderException;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class PaymentOrderQueue {

    private final BlockingQueue<PaymentOrder> paymentOrderBlockingQueue;

    private final PaymentOrderProcessor paymentOrderProcessor;

    public PaymentOrderQueue(PaymentOrderProcessor paymentOrderProcessor, BlockingQueue<PaymentOrder> paymentOrderBlockingQueue) {
        this.paymentOrderProcessor = paymentOrderProcessor;
        this.paymentOrderBlockingQueue = paymentOrderBlockingQueue;
        paymentOrderProcessor.start();
    }

    public PaymentOrder getPaymentOrderFromQueue() {
        PaymentOrder paymentOrder;
        try {
            paymentOrder = paymentOrderBlockingQueue.take();
        } catch (InterruptedException ex) {
            throw new UnableToProcessPaymentOrderException();
        }

        return paymentOrder;
    }

    public UUID putPaymentOrder(PaymentOrder paymentOrder) {
        try {
            paymentOrderBlockingQueue.put(paymentOrder);
        } catch (InterruptedException ex) {
            throw new UnableToProcessPaymentOrderException();
        }
        return UUID.randomUUID();
    }

}
