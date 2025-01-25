package org.example.cosmeticskinandlasercenter.billing.dto;

import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDTO {
    private Long invoiceId;
    private Long patientId;
    private Long appointmentId;
    private LocalDate invoiceDate;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal tax;
    private BigDecimal netAmount;
    private InvoiceStatus status;
    private LocalDate dueDate;
    private String notes;
    private List<PaymentDTO> payments;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}