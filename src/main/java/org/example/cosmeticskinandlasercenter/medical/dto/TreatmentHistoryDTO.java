package org.example.cosmeticskinandlasercenter.medical.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TreatmentHistoryDTO {
    private Long historyId;
    private Long recordId;
    private Long treatmentId;
    private Long staffId;
    private Long appointmentId;
    private LocalDateTime treatmentDate;
    private String notes;
    private String outcome;
    private String createdBy;
    private String lastModifiedBy;
}
