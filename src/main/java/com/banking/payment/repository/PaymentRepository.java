package com.banking.payment.repository;

import com.banking.payment.exception.InvalidPaymentException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class PaymentRepository {

  Logger logger = Logger.getLogger(PaymentRepository.class.getName());

  private final Map<UUID, Payment> paymentsContainer = new ConcurrentHashMap<UUID, Payment>();

  private final PaymentOrderQueue paymentOrderQueue;

  public PaymentRepository(PaymentOrderQueue paymentOrderQueue) {
    this.paymentOrderQueue = paymentOrderQueue;
  }

  public UUID acceptFundTransferPaymentOrder(FundTransferPaymentOrder paymentOrder) {
    final UUID paymentOrderId = paymentOrderQueue.putPaymentOrder(paymentOrder);
    paymentsContainer.put(
        paymentOrderId, new Payment(paymentOrder, "Initiated", PaymentStatus.PENDING));
    return paymentOrderId;
  }

  public UUID acceptDepositPaymentOrder(DepositPaymentOrder paymentOrder) {
      final UUID paymentOrderId = paymentOrderQueue.putPaymentOrder(paymentOrder);
      paymentsContainer.put(
              paymentOrderId, new Payment(paymentOrder, "Initiated", PaymentStatus.PENDING));
      return paymentOrderId;
  }

  public void processPaymentOrder(AbstractPaymentOrder paymentOrder, String message, PaymentStatus status) {
    logger.info(String.format("Processing Payment Order %s", paymentOrder));
    paymentsContainer.get(paymentOrder.getId()).updateState(message, status);
  }

  public Payment getPayment(UUID paymentId) {

    if (!paymentsContainer.containsKey(paymentId)) {
      throw new InvalidPaymentException(
          "No Payment found with this Payment Id " + paymentId.toString());
    }
    return paymentsContainer.get(paymentId);
  }

}
