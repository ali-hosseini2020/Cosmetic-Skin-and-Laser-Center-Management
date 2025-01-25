package org.example.cosmeticskinandlasercenter.billing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.PaymentMethod;
import org.example.cosmeticskinandlasercenter.common.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    @NotNull
    private Long invoiceId;
    @NotNull
    @PastOrPresent
    private LocalDateTime paymentDate;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull
    private PaymentStatus paymentStatus;
    private Long staffId;
    private String transactionId;
    private String notes;
}