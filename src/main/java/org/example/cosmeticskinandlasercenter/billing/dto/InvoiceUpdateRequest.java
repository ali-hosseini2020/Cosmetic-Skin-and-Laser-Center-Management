package org.example.cosmeticskinandlasercenter.billing.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceUpdateRequest {
    @PastOrPresent
    private LocalDate invoiceDate;
    @PositiveOrZero
    private BigDecimal totalAmount;
    @PositiveOrZero
    private BigDecimal discount;
    @PositiveOrZero
    private BigDecimal tax;
    @PositiveOrZero
    private BigDecimal netAmount;
    private InvoiceStatus status;
    private LocalDate dueDate;
    private String notes;
}