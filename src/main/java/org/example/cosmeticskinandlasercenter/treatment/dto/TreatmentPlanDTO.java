package org.example.cosmeticskinandlasercenter.treatment.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for TreatmentPlan entity.
 */
@Data
@NoArgsConstructor
public class TreatmentPlanDTO {
    private Long planId;
    private String planName;
    private String notes;
    private Long patientId;
    private List<TreatmentDTO> treatments;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}