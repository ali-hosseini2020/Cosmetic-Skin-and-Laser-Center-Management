package org.example.cosmeticskinandlasercenter.billing.dto;

import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.PaymentMethod;
import org.example.cosmeticskinandlasercenter.common.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long paymentId;
    private Long invoiceId;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Long staffId; // ID of the staff member who processed the payment
    private String transactionId;
    private String notes;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}