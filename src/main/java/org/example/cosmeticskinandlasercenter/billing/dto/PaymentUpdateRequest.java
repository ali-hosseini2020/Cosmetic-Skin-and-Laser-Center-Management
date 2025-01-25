package org.example.cosmeticskinandlasercenter.billing.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.PaymentMethod;
import org.example.cosmeticskinandlasercenter.common.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentUpdateRequest {
    @PastOrPresent
    private LocalDateTime paymentDate;
    @Positive
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Long staffId;
    private String transactionId;
    private String notes;
}