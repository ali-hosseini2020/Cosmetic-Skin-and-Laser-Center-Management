package org.example.cosmeticskinandlasercenter.billing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceRequest {
    @NotNull
    private Long patientId;
    @NotNull
    private Long appointmentId;
    @NotNull
    @PastOrPresent
    private LocalDate invoiceDate;
    @NotNull
    @PositiveOrZero
    private BigDecimal totalAmount;
    @PositiveOrZero
    private BigDecimal discount;
    @PositiveOrZero
    private BigDecimal tax;
    @NotNull
    @PositiveOrZero
    private BigDecimal netAmount;
    @NotNull
    private InvoiceStatus status;
    private LocalDate dueDate;
    private String notes;
}