package org.example.cosmeticskinandlasercenter.medical.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TreatmentHistoryUpdateRequest {
    private Long treatmentId;
    private Long staffId;
    private Long appointmentId;
    private LocalDateTime treatmentDate;
    private String notes;
    private String outcome;
}
