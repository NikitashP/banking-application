package com.banking.payment.repository;

import java.math.BigDecimal;
import java.util.UUID;

public class AbstractPaymentOrder {
  protected final UUID id;
  protected UUID beneficiaryAccountId;
  protected BigDecimal amount;

  public AbstractPaymentOrder() {
    id = UUID.randomUUID();
  }

  public UUID getBeneficiaryAccountId() {
    return beneficiaryAccountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public UUID getId() {
    return id;
  }
}
