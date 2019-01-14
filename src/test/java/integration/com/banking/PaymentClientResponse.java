package integration.com.banking;

import com.banking.payment.repository.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentClientResponse {

    private UUID id;
    private UUID payeeAccountId;
    private  UUID beneficiaryAccountId;
    private BigDecimal amount;
    private String message;
    private PaymentStatus status;

    public PaymentClientResponse() {
    }

    public UUID getId() {
        return id;
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

    public String getMessage() {
        return message;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
